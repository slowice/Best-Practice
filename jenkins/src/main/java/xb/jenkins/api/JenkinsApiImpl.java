package xb.jenkins.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xb.common.entity.Result;
import xb.jenkins.service.JenkinsHelper;

@RestController
public class JenkinsApiImpl implements JenkinsApi{

    @Autowired
    JenkinsHelper jenkinsHelper;


    @PostMapping("/job/create")
    @Override
    public Result createJob(@RequestPart("jobName")String jobName, @RequestPart(name = "jobConfigXml", required = false)String jobConfigXml) {
        jenkinsHelper.createJob(jobName, jobConfigXml);
        return new Result(Result.OK);
    }

    @PostMapping("/job/start")
    @Override
    public Result startJob(@RequestParam String jobName) {
        jenkinsHelper.startJob(jobName);
        return new Result(Result.OK);
    }
}
