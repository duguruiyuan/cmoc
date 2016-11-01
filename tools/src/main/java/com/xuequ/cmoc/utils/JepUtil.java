package com.xuequ.cmoc.utils;

import java.math.BigDecimal;

import org.nfunk.jep.JEP;

/**
 * @author 000000022
 * Jep一些计算
 */
public class JepUtil {
	/**
	 * 
	 */
	private static String monthAmt_formula = "(B / n) + (B *h)";
	
	public final static int RETAIN_4 = 4;
	
	public final static int RETAIN_2 = 2;

	private static int DECIMAL_DOWN = BigDecimal.ROUND_DOWN; //小数截取方式，默认为舍去
	
	private static int DECIMAL_UP = BigDecimal.ROUND_UP; //直接进位
	
	private static int ROUND_HALF_UP = BigDecimal.ROUND_HALF_UP; //四舍五入
	
//	private static String fixed_dayAmt_formula = "A * y / 365 * days";
	
	private static String fixed_dayAmt_formula = "A * (y / 12) * term";
	
	private static String unFixed_dayAmt_formula = "A *(0.01- y)*12 / 365 * days";
	
	/**
	 * 向上截取2位
	 * @param bg
	 * @return
	 */
    public static BigDecimal upAmt(BigDecimal bg){
    	return bg.setScale(RETAIN_2, DECIMAL_UP);
    }
    
	/**
	 * 向下截取2位
	 * @param bg
	 * @return
	 */
    public static BigDecimal downAmt(BigDecimal bg){
    	return bg.setScale(RETAIN_2, DECIMAL_DOWN);
    }
    
	/**
	 * 四舍五入
	 * @param bg
	 * @return
	 */
    public static BigDecimal halfUp(BigDecimal bg){
    	return bg.setScale(RETAIN_2, ROUND_HALF_UP);
    }
    
    /**
     * 格式化为4位
     * @param bg
     * @return
     */
    public static BigDecimal formatAmt(BigDecimal bg){
    	return bg.setScale(RETAIN_4, DECIMAL_DOWN);
    }
    
    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        return v1.divide(v2, scale, BigDecimal.ROUND_DOWN);
    }
	
	public static BigDecimal getMonthAmount(BigDecimal amount, BigDecimal jfRate, Integer termNo){
		JEP jep = new JEP();
		jep.addVariable("B", amount); // 放款金额
        jep.addVariable("h", jfRate); // 总费率
        jep.addVariable("n", termNo); // 还款总期数
        jep.parseExpression(monthAmt_formula);
        double monthAmt = jep.getValue();
        BigDecimal monthAmtDecimal = AmountArithUtil.formatAmount(new BigDecimal(monthAmt));
		return monthAmtDecimal;
	}
	
	
	public static BigDecimal getFixedAmt(BigDecimal loanAmt,BigDecimal yearRate,BigDecimal term){
		
		JEP jep = new JEP(); // 计费公式编辑器
        jep.addVariable("A", loanAmt); 
        jep.addVariable("y", yearRate); 
        jep.addVariable("term", term);
        jep.parseExpression(fixed_dayAmt_formula);
//        log.debug(jep.getValue());
//      BigDecimal sumInterestDecimal =  new BigDecimal(jep.getValue()).setScale(2, BigDecimal.ROUND_HALF_UP);		//总利息	四舍五入
        BigDecimal sumInterestDecimal =  new BigDecimal(jep.getValue()).setScale(2, BigDecimal.ROUND_DOWN);	//保留两位小数
        return sumInterestDecimal;
	}
	
	
//	（  投资本金 *（年化收益率 / 12月 ） * 封闭期）
		
	
	public static BigDecimal getUnFixedAmt(BigDecimal loanAmt,BigDecimal yearRate,BigDecimal days){
		JEP jep = new JEP(); // 计费公式编辑器
        jep.addVariable("A", loanAmt); 
        jep.addVariable("y", yearRate); 
        jep.addVariable("days", days);
        jep.parseExpression(unFixed_dayAmt_formula);
        BigDecimal sumInterestDecimal =  new BigDecimal(jep.getValue()).setScale(2, BigDecimal.ROUND_DOWN);	//保留两位小数
        return sumInterestDecimal;
	}
	
	
	public static void main(String[] args) {			
/*		BigDecimal days = new BigDecimal(31);
		//当期还款计划应付利息
		BigDecimal interestAmt = new BigDecimal("222.44");
		//当日应付利息
		BigDecimal everyPay = div(interestAmt,days,RETAIN_4);
		System.out.println(everyPay);*/
		
		
		/*System.out.println(getFixedAmt(new BigDecimal(100.00),new BigDecimal(0.090000000),new BigDecimal(182)));
		System.out.println(getFixedAmt(new BigDecimal(200.00),new BigDecimal(0.090000000),new BigDecimal(182)));
		System.out.println(getFixedAmt(new BigDecimal(161.74),new BigDecimal(0.080000000),new BigDecimal(183)));
		System.out.println(getFixedAmt(new BigDecimal(100.00),new BigDecimal(0.080000000),new BigDecimal(91)));*/
		
		System.out.println(getFixedAmt(new BigDecimal(161.74),new BigDecimal(0.080000000),new BigDecimal(91)));
		System.out.println(getUnFixedAmt(new BigDecimal(10000.00),new BigDecimal(0.001700000),new BigDecimal(92)));
		
		
		
		
	}
}
