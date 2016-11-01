package com.xuequ.cmoc.utils;



public  class IdGeneratorUtil {

	private final long workerId; //生成的64位字节  长度为16的序列号

	private final static long twepoch = 1361753741828L;

	private long sequence = 0L;
	 
	private final static long workerIdBits = 4L; //机器标识位数

	public final static long maxWorkerId = -1L ^ -1L << workerIdBits; //数据中心ID最大值 //二进制数据应该是1111 1111 1111 1111 ^ 1111 1111 1111 0000 = 0000 0000 0000 1111

	private final static long sequenceBits = 10L; //毫秒内自增位

	private final static long workerIdShift = sequenceBits;  //机器ID偏左移10位

	private final static long timestampLeftShift = sequenceBits + workerIdBits; //数据中心ID左移15位


	public final static long sequenceMask = -1L ^ -1L << sequenceBits; //二进制数据应该是1111 1111 1111 1111 ^ 1111 1100 0000 0000 = 0000 0011 1111 1111

	private long lastTimestamp = -1L;

	public IdGeneratorUtil(final long workerId) {

		super();

		if (workerId > this.maxWorkerId || workerId < 0) {

			throw new IllegalArgumentException(String.format(

					"worker Id can't be greater than %d or less than 0",

					this.maxWorkerId));

		}

		this.workerId = workerId;

	}

	public  synchronized long generateUniqueNo() {

		long timestamp = System.currentTimeMillis(); //1464855974573
//针对lastTimestamp 与当前时间戳的大小情况，处理sequence，或者重新给当前timestamp赋值
		if (this.lastTimestamp == timestamp) {

			this.sequence = (this.sequence + 1) & this.sequenceMask; //&0000 0011 1111 1111  即截取sequence去反码后截取后10位

			if (this.sequence == 0) {

				System.out.println("###########" + sequenceMask);

				timestamp = this.tilNextMillis(this.lastTimestamp); //如果当前时间=上一次时间，切sequence

			}

		} else {

			this.sequence = 0;

		}

		if (timestamp < this.lastTimestamp) {

			try {

				throw new Exception(

						String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",

								this.lastTimestamp - timestamp));

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		this.lastTimestamp = timestamp;

		long nextId = ((timestamp - twepoch << timestampLeftShift))

				| (this.workerId << this.workerIdShift) | (this.sequence); //核心算法 机器号？ 6个数据计算得出生成的序号
          // (最后一次的时间戳 - twepoch(指 2013-02-25日期数据的times)  << timestampLeftShift数据中心ID左移15位) | workerId默认值<< workerIdShift(机器ID偏左移10位,毫秒内自增位) | sequence默认值
                          
		

		return nextId;

	}

	/*此处是否是考虑有篡改系统时间的风险，万一系统时间被更改，
	 *得一直等到时间过了才能执行，
	 *否则陷入while循环出不去（此算法可以保证每次使用时的时间都在之前的后面）*/
	private long tilNextMillis(final long lastTimestamp) {

		long timestamp = System.currentTimeMillis();

		while (timestamp <= lastTimestamp) {

			timestamp = System.currentTimeMillis();

		}

		return timestamp;

	}

	public static long getUniqueId(){
		IdGeneratorUtil worker = new IdGeneratorUtil(2);
		
		return worker.generateUniqueNo();
		
		
	}


}
