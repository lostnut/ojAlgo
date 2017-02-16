/*
 * Copyright 1997-2017 Optimatika (www.optimatika.se)
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
package org.ojalgo.array;

import org.ojalgo.constant.PrimitiveMath;
import org.ojalgo.scalar.Scalar;

public abstract class PrimitiveArray extends PlainArray<Double> {

    public static final DenseArray.Factory<Double> FACTORY = new DenseArray.Factory<Double>() {

        @Override
        long getElementSize() {
            return Primitive64Array.FACTORY.getElementSize();
        }

        @Override
        DenseArray<Double> make(final long size) {
            return Primitive64Array.FACTORY.make(size);
        }

        @Override
        Scalar<Double> zero() {
            return Primitive64Array.FACTORY.zero();
        }

    };

    public static PrimitiveArray make(final int size) {
        return Primitive64Array.make(size);
    }

    public static PrimitiveArray wrap(final double[] data) {
        return Primitive64Array.wrap(data);
    }

    PrimitiveArray(final int size) {
        super(size);
    }

    @Override
    final boolean isPrimitive() {
        return true;
    }

    @Override
    final void reset() {
        this.fillAll(PrimitiveMath.ZERO);
    }

}
