package xb.jenkins.service;

public interface JenkinsHelper {
    String createJob(String jobName, String jobConfigXml);

    String startJob(String jobName);
}
