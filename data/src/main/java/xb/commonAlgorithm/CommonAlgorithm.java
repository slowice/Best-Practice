package xb.commonAlgorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@RunWith(SpringRunner.class)
public class CommonAlgorithm {
    /**
     * Hash函数，用于将任意长度简化为128位长度的编码
     */
    public void hashTest(){

    }

    //region 递归
    /**
     * 递归.
     * 使用递归要注意几个点
     * 1.函数调用自身
     * 2.有递归出口
     * 3.父问题和子问题不能有重叠的部分
     */
    //以下是递归算法的一般形式
    public void func(){
        //函数出口
        if(1==1){
            return;
        }else {
            func();//调用自身
        }
    }
    static List<String> fileDirectoryList = new LinkedList<>();
    static List<String> fileList = new LinkedList<>();

    //递归显示目录 11337 287
    @Test
    public void reInvokeTest() throws InterruptedException, ExecutionException {
        //listAllFile(new File("/Users/xubin/document"));
        ExecutorService service = Executors.newFixedThreadPool(100);
        FutureTask<String> task = new FutureTask<String>(new task());
        service.submit(task);
        System.out.println(task.get());
        log.info(String.valueOf(fileList.size()));
        log.info(String.valueOf(fileDirectoryList.size()));
    }
    public  void listAllFile(File file){
        if(file == null){
            log.error("target file is null");
            return;
        }
        File[] children = file.listFiles();
        if(children == null){
            fileList.add(file.getName());
            return;
        }
        for(File child:children){
            if(child == null){
                continue;
            }
            String directoryName = child.getName();
            if(child.isDirectory()){
                //log.info("文件夹名字是: "+directoryName);
                fileDirectoryList.add(directoryName);
                listAllFile(child);
            }else {
                //log.info("文件名是: "+directoryName);
                fileList.add(directoryName);
            }
        }
    }
    //endregion
    class task implements Callable {
        @Override
        public Object call() throws Exception {
            listAllFile(new File("/Users/xubin"));
            return "ok";
        }
    }

}
