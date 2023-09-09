package xb.api;

import xb.entity.Result;

public interface JenkinsApi {
    Result createJob(String jobName, String jobConfigXml);
    Result startJob(String jobName);
}
