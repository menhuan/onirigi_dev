package com.test.demo.task;

/**
 * 任务执行的 子接口
 * @author dell
 *
 */
public interface JobExecuteSub extends JobExecute {

	public void run () throws Exception;
}
