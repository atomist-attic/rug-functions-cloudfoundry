package com.atomist.rug.function.cf;

import org.cloudfoundry.doppler.Envelope;
import org.cloudfoundry.doppler.RecentLogsRequest;
import org.cloudfoundry.operations.DefaultCloudFoundryOperations;
import org.cloudfoundry.operations.applications.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Operations {

    private final DefaultCloudFoundryOperations operations;

    public Operations(DefaultCloudFoundryOperations operations) {
        this.operations = operations;
    }

    public List<String> recentLog(String appName) {
        ApplicationDetail apps = operations.applications().get(GetApplicationRequest.builder().name(appName).build()).block();
        List<Envelope> envelope = operations.getDopplerClient().recentLogs(RecentLogsRequest.builder().applicationId(apps.getId()).build()).collectList().block();
        envelope.sort(Comparator.comparing(c -> c.getLogMessage().getTimestamp()));
        return envelope.stream().map(c -> c.getLogMessage())
                .map(c -> appName + ":" + c.getSourceInstance() + " - " + c.getMessage()).collect(Collectors.toList());
    }

    public void stop(String appName) {
        operations.applications().stop(StopApplicationRequest.builder().name(appName).build()).block();
    }

    public void start(String appName) {
        operations.applications().start(StartApplicationRequest.builder().name(appName).build()).block();
    }

    public void scale(String appName, Integer instances) {
        operations.applications().scale(ScaleApplicationRequest.builder().name(appName).instances(instances).build()).block();
    }

    public ApplicationDetail info(String appName) {
        return operations.applications().get(GetApplicationRequest.builder().name(appName).build()).block();
    }
}
