/*
 * The MIT License
 *
 * Copyright (c) 2017 aoju.org All rights reserved.
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.aoju.bus.cache.reader;

import org.aoju.bus.cache.entity.CacheHolder;
import org.aoju.bus.cache.entity.CacheMethod;
import org.aoju.bus.cache.invoker.BaseInvoker;
import org.aoju.bus.logger.Logger;

/**
 * @author Kimi Liu
 * @version 3.5.2
 * @since JDK 1.8
 */
public abstract class AbstractCacheReader {

    public abstract Object read(CacheHolder cacheHolder, CacheMethod cacheMethod, BaseInvoker baseInvoker, boolean needWrite) throws Throwable;

    Object doLogInvoke(ThrowableSupplier<Object> throwableSupplier) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return throwableSupplier.get();
        } finally {
            Logger.debug("method invoke total cost [{}] ms", (System.currentTimeMillis() - start));
        }
    }

    @FunctionalInterface
    protected interface ThrowableSupplier<T> {
        T get() throws Throwable;
    }
}
