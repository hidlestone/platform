package com.fallframework.platform.starter.task.quartz.util;

import com.fallframework.platform.starter.task.quartz.model.JobRequest;
import com.fallframework.platform.starter.task.quartz.model.TriggerTypeEnum;
import org.quartz.CronScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

/**
 * @author zhuangpf
 */
public class QrtzTaskUtil {

	/**
	 * 根据触发器类型选择构建触发器(默认是表达式触发器)
	 *
	 * @param triggerKey 触发器联合主键
	 * @param request    请求入参
	 * @return 构建的触发器
	 */
	public static Trigger constructTrigger(TriggerKey triggerKey, JobRequest request) {
		Trigger trigger;
		if (request.getTriggerType().equals(TriggerTypeEnum.SIMPLE)) {
			trigger = constructSimpleTrigger(triggerKey, request);
		} else {
			trigger = constructCronTrigger(triggerKey, request);
		}
		return trigger;
	}

	/**
	 * 构建简单触发器
	 *
	 * @param triggerKey 触发器key
	 * @param request    请求参数
	 * @return 简单触发器
	 */
	public static Trigger constructSimpleTrigger(TriggerKey triggerKey, JobRequest request) {
		SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().
				withIntervalInSeconds(request.getIntervalInSeconds()).withRepeatCount(request.getRepeatCount());
		setSimpleMisFireType(request, simpleScheduleBuilder);
		return TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(request.getTriggerDescription())
				.withSchedule(simpleScheduleBuilder).build();
	}

	/**
	 * 简单失败处理指令选择策略
	 *
	 * @param request               请求参数
	 * @param simpleScheduleBuilder 简单触发器构建对象
	 */
	public static void setSimpleMisFireType(JobRequest request, SimpleScheduleBuilder simpleScheduleBuilder) {
		switch (request.getSimpleMisfire()) {
			case FIRE_NOW:
				// 以当前时间为触发频率立即触发执行
				// 执行至FinalTIme的剩余周期次数
				// 以调度或恢复调度的时刻为基准的周期频率，FinalTime根据剩余次数和当前时间计算得到
				// 调整后的FinalTime会略大于根据starttime计算的到的FinalTime值
				simpleScheduleBuilder.withMisfireHandlingInstructionFireNow();
				break;
			case IGNORE_MISFIRES:
				// 以错过的第一个频率时间立刻开始执行
				// 重做错过的所有频率周期
				// 当下一次触发频率发生时间大于当前时间以后，按照Interval的依次执行剩下的频率
				// 共执行RepeatCount+1次
				simpleScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
				break;
			case NEXT_WITH_EXISTING_COUNT:
				// 不触发立即执行
				// 等待下次触发频率周期时刻，执行至FinalTime的剩余周期次数
				// 以startTime为基准计算周期频率，并得到FinalTime
				// 即使中间出现pause，resume以后保持FinalTime时间不变
				simpleScheduleBuilder.withMisfireHandlingInstructionNextWithExistingCount();
				break;
			case NOW_WITH_EXISTING_COUNT:
				// ——以当前时间为触发频率立即触发执行
				// ——执行至FinalTIme的剩余周期次数
				// ——以调度或恢复调度的时刻为基准的周期频率，FinalTime根据剩余次数和当前时间计算得到
				// ——调整后的FinalTime会略大于根据starttime计算的到的FinalTime值
				// 总结:失效之后，再启动之后马上执行，但是起始次数清零，总次数=7+当前misfire执行次数-1
				simpleScheduleBuilder.withMisfireHandlingInstructionNowWithExistingCount();
				break;
			case NEXT_WITH_REMAINING_COUNT:
				// ——不触发立即执行
				// ——等待下次触发频率周期时刻，执行至FinalTime的剩余周期次数
				// ——以startTime为基准计算周期频率，并得到FinalTime
				// ——即使中间出现pause，resume以后保持FinalTime时间不变
				simpleScheduleBuilder.withMisfireHandlingInstructionNextWithRemainingCount();
				break;
			case NOW_WITH_REMAINING_COUNT:
				// ——以当前时间为触发频率立即触发执行
				// ——执行至FinalTIme的剩余周期次数
				// ——以调度或恢复调度的时刻为基准的周期频率，FinalTime根据剩余次数和当前时间计算得到
				// ——调整后的FinalTime会略大于根据starttime计算的到的FinalTime值
				simpleScheduleBuilder.withMisfireHandlingInstructionNowWithRemainingCount();
			default:
				// 默认值
				simpleScheduleBuilder.withMisfireHandlingInstructionFireNow();
				break;
		}
	}

	/**
	 * 构建表达式CRON触发器
	 *
	 * @param triggerKey 触发器key
	 * @param request    请求参数
	 * @return CRON触发器
	 */
	public static Trigger constructCronTrigger(TriggerKey triggerKey, JobRequest request) {
		int triggerPriority = request.getTriggerPriority();
		triggerPriority = triggerPriority == 0 ? 5 : triggerPriority;
		//表达式触发器
		CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(request.getCronExpression());
		setCronMisFireType(request, schedBuilder);
		return TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(request.getTriggerDescription()).
				withPriority(triggerPriority).withSchedule(schedBuilder).build();
	}

	/**
	 * 表达式失败处理指令选择策略
	 *
	 * @param request             请求参数
	 * @param cronScheduleBuilder CRON触发器构建对象
	 */
	public static void setCronMisFireType(JobRequest request, CronScheduleBuilder cronScheduleBuilder) {
		switch (request.getCronMisfire()) {
			case DO_NOTHING:
				// 不触发立即执行,等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
				cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
				break;
			case FIRE_ONCE_NOW:
				// 以当前时间为触发频率立刻触发一次执行
				// 然后按照Cron频率依次执行
				cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
				break;
			case IGNORE_MISFIRES:
				// 以错过的第一个频率时间立刻开始执行
				// 重做错过的所有频率周期后
				// 当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行
				cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
				break;
			default:
				// 默认值
				// 不触发立即执行,等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
				cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
		}
	}

}
