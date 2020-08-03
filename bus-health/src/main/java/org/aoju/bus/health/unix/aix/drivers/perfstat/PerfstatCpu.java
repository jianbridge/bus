/*********************************************************************************
 *                                                                               *
 * The MIT License (MIT)                                                         *
 *                                                                               *
 * Copyright (c) 2015-2020 aoju.org OSHI and other contributors.                 *
 *                                                                               *
 * Permission is hereby granted, free of charge, to any person obtaining a copy  *
 * of this software and associated documentation files (the "Software"), to deal *
 * in the Software without restriction, including without limitation the rights  *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell     *
 * copies of the Software, and to permit persons to whom the Software is         *
 * furnished to do so, subject to the following conditions:                      *
 *                                                                               *
 * The above copyright notice and this permission notice shall be included in    *
 * all copies or substantial portions of the Software.                           *
 *                                                                               *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR    *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,      *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE   *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER        *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN     *
 * THE SOFTWARE.                                                                 *
 ********************************************************************************/
package org.aoju.bus.health.unix.aix.drivers.perfstat;

import org.aoju.bus.core.annotation.ThreadSafe;
import org.aoju.bus.health.unix.aix.Perfstat;

/**
 * Utility to query performance stats for cpu
 *
 * @author Kimi Liu
 * @version 6.0.5
 * @since JDK 1.8+
 */
@ThreadSafe
public final class PerfstatCpu {

    private static final Perfstat PERF = Perfstat.INSTANCE;

    private PerfstatCpu() {
    }

    /**
     * Queries perfstat_cpu_total for total CPU usage statistics
     *
     * @return usage statistics
     */
    public static Perfstat.perfstat_cpu_total_t queryCpuTotal() {
        Perfstat.perfstat_cpu_total_t cpu = new Perfstat.perfstat_cpu_total_t();
        int ret = PERF.perfstat_cpu_total(null, cpu, cpu.size(), 1);
        if (ret > 0) {
            return cpu;
        }
        return new Perfstat.perfstat_cpu_total_t();
    }

    /**
     * Queries perfstat_cpu for per-CPU usage statistics
     *
     * @return an array of usage statistics
     */
    public static Perfstat.perfstat_cpu_t[] queryCpu() {
        Perfstat.perfstat_cpu_t cpu = new Perfstat.perfstat_cpu_t();
        // With null, null, ..., 0, returns total # of elements
        int cputotal = PERF.perfstat_cpu(null, null, cpu.size(), 0);
        if (cputotal > 0) {
            Perfstat.perfstat_cpu_t[] statp = (Perfstat.perfstat_cpu_t[]) cpu.toArray(cputotal);
            Perfstat.perfstat_id_t firstcpu = new Perfstat.perfstat_id_t(); // name is ""
            int ret = PERF.perfstat_cpu(firstcpu, statp, cpu.size(), cputotal);
            if (ret > 0) {
                return statp;
            }
        }
        return new Perfstat.perfstat_cpu_t[0];
    }

    /**
     * Returns affinity mask from the number of CPU in the OS.
     *
     * @return affinity mask
     */
    public static long queryCpuAffinityMask() {
        int cpus = queryCpuTotal().ncpus;
        if (cpus < 63) {
            return (1L << cpus) - 1;
        }
        return cpus == 63 ? Long.MAX_VALUE : -1L;
    }

}
