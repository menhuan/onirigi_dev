package com.test.demo.spark;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SparkTest implements Serializable {

	
	@Autowired
	private transient JavaSparkContext  scContext;
	
	public void  sparkContextTest() {
		SparkConf  conf = new SparkConf().setMaster("local").setAppName("My App Test");
		
		JavaRDD<String>  pairRDD =  scContext.parallelize(Arrays.asList("a","b","c"));
		
		pairRDD.map(result ->  result.split(" "));  
		
		JavaRDD<String> resultRdd=pairRDD.filter( content  -> {
			return  content.equals('s') ; 
		}) ;
	
	}
	
}
