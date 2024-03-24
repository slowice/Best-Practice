package xb.jenkins.api;

import xb.common.entity.Result;

public interface JenkinsApi {
    Result createJob(String jobName, String jobConfigXml);
    Result startJob(String jobName);
}
