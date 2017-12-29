/*
 * Copyright 1997-2017 Optimatika
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
package org.ojalgo.matrix.geometry;

import java.io.Serializable;

import org.ojalgo.matrix.store.MatrixStore;
import org.ojalgo.matrix.store.PhysicalStore;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

interface GeometryMatrix<MT extends GeometryMatrix<? super MT>> extends MatrixStore<Double>, Serializable {

    double doubleValue(int row, int col);

    default double doubleValue(final long row, final long col) {
        return MatrixStore.super.doubleValue((int) row, (int) col);
    }

    default Double get(final long row, final long col) {
        return MatrixStore.super.doubleValue((int) row, (int) col);
    }

    @SuppressWarnings("unchecked")
    default MT negate() {
        final MT retVal = this.newInstance();
        retVal.negate((MT) this);
        return retVal;
    }

    void negate(MT matrix);

    /**
     * package private
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    default MT newInstance() {
        try {
            return (MT) this.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            return null;
        }
    }

    default PhysicalStore.Factory<Double, PrimitiveDenseStore> physical() {
        return PrimitiveDenseStore.FACTORY;
    }

    @SuppressWarnings("unchecked")
    default MT transpose() {
        final MT retVal = this.newInstance();
        retVal.transpose((MT) this);
        return retVal;
    }

    void transpose(MT matrix);

}
