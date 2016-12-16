package com.xuequ.cmoc;

import com.jacob.activeX.ActiveXComponent;  
import com.jacob.com.Dispatch;  
import com.jacob.com.Variant;

public class wordtohtml {
	public static boolean wordToHtml(String inPath, String toPath) {
        // 启动word  
        ActiveXComponent axc = new ActiveXComponent("Word.Application");  
  
        boolean flag = false;  
  
        try {  
            // 设置word不可见  
            axc.setProperty("Visible", new Variant(false));  
  
            Dispatch docs = axc.getProperty("Documents").toDispatch();  
  
            // 打开word文档  
            Dispatch doc = Dispatch.invoke(  
                    docs,  
                    "Open",  
                    Dispatch.Method,  
                    new Object[] { inPath, new Variant(false),  
                            new Variant(true) }, new int[1]).toDispatch();  
  
            // 作为html格式保存到临时文件  
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {  
                    toPath, new Variant(8) }, new int[1]);  
  
            Variant f = new Variant(false);  
            Dispatch.call(doc, "Close", f);  
            flag = true;  
            return flag;  
  
        } catch (Exception e) {  
            e.printStackTrace();  
            return flag;  
        } finally {  
            axc.invoke("Quit", new Variant[] {});  
        }  
    }  
	
	public static void main(String[] args) {  
        
		wordtohtml.wordToHtml("C:\\Users\\000001436\\Downloads\\陶学趣报名表.doc",  
                "C://Users//000001436//Downloads//aa.htm");  
  
    }  
}
