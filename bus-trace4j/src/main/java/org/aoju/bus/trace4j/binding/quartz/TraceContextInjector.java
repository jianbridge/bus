package org.aoju.bus.trace4j.binding.quartz;

import org.aoju.bus.trace4j.Builder;
import org.aoju.bus.trace4j.Backend;
import org.aoju.bus.trace4j.consts.TraceConsts;
import org.aoju.bus.trace4j.config.TraceFilterConfiguration;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;

public class TraceContextInjector {

    private final Backend backend;

    private final String profile;

    public TraceContextInjector() {
        this(Builder.getBackend(), TraceConsts.DEFAULT);
    }

    public TraceContextInjector(final String profile) {
        this(Builder.getBackend(), profile);
    }

    TraceContextInjector(final Backend backend, final String profile) {
        this.backend = backend;
        this.profile = profile;
    }

    public void injectContext(Trigger trigger) {
        injectContext(trigger.getJobDataMap());
    }

    public void injectContext(JobDetail jobDetail) {
        injectContext(jobDetail.getJobDataMap());
    }

    public void injectContext(JobDataMap jobDataMap) {
        final TraceFilterConfiguration configuration = backend.getConfiguration(profile);
        if (!backend.isEmpty() && configuration.shouldProcessContext(TraceFilterConfiguration.Channel.AsyncDispatch)) {
            jobDataMap.put(TraceConsts.TPIC_HEADER,
                    backend.getConfiguration(profile).filterDeniedParams(backend.copyToMap(),
                            TraceFilterConfiguration.Channel.AsyncDispatch));
        }
    }

}