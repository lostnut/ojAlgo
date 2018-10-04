/*
 * Copyright 1997-2018 Optimatika
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.ojalgo.matrix.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ojalgo.TestUtils;
import org.ojalgo.function.aggregator.Aggregator;
import org.ojalgo.random.Uniform;

public class SparseCase extends NonPhysicalTest {

    @BeforeEach
    public void setUp() {

        final int dim = Uniform.randomInteger(1, 9);

        rationalStore = SparseStore.RATIONAL.make(dim, dim);
        complexStore = SparseStore.COMPLEX.make(dim, dim);
        primitiveStore = SparseStore.PRIMITIVE.make(dim, dim);

        for (int ij = 0; ij < dim; ij++) {
            ((SparseStore<?>) rationalStore).set(ij, ij, 1.0);
            ((SparseStore<?>) complexStore).set(ij, ij, 1.0);
            ((SparseStore<?>) primitiveStore).set(ij, ij, 1.0);
        }
    }

    @Test
    public void testOneFullColumn() {

        final int ind = Uniform.randomInteger(0, 10);

        SparseStore<Double> store = SparseStore.PRIMITIVE.make(10, 10);
        store.fillColumn(ind, 1.0);

        for (int i = 0; i < 10; i++) {
            double sum = store.aggregateRow(i, Aggregator.SUM);
            double prod = store.aggregateRow(i, Aggregator.PRODUCT);
            TestUtils.assertEquals(1.0, sum);
            TestUtils.assertEquals(0.0, prod);
        }

        for (int j = 0; j < 10; j++) {
            double sum = store.aggregateColumn(j, Aggregator.SUM);
            double prod = store.aggregateColumn(j, Aggregator.PRODUCT);
            if (j == ind) {
                TestUtils.assertEquals(10.0, sum);
                TestUtils.assertEquals(1.0, prod);
            } else {
                TestUtils.assertEquals(0.0, sum);
                TestUtils.assertEquals(0.0, prod);
            }
        }
    }

    @Test
    public void testOneFullRow() {

        final int ind = Uniform.randomInteger(0, 10);

        SparseStore<Double> store = SparseStore.PRIMITIVE.make(10, 10);
        store.fillRow(ind, 1.0);

        for (int i = 0; i < 10; i++) {
            double sum = store.aggregateRow(i, Aggregator.SUM);
            double prod = store.aggregateRow(i, Aggregator.PRODUCT);
            if (i == ind) {
                TestUtils.assertEquals(10.0, sum);
                TestUtils.assertEquals(1.0, prod);
            } else {
                TestUtils.assertEquals(0.0, sum);
                TestUtils.assertEquals(0.0, prod);
            }
        }

        for (int j = 0; j < 10; j++) {
            double sum = store.aggregateColumn(j, Aggregator.SUM);
            double prod = store.aggregateColumn(j, Aggregator.PRODUCT);
            TestUtils.assertEquals(1.0, sum);
            TestUtils.assertEquals(0.0, prod);
        }
    }

}
