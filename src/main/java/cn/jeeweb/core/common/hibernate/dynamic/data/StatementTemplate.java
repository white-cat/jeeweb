package cn.jeeweb.core.common.hibernate.dynamic.data;  
import java.io.Serializable;

import freemarker.template.Template;  
  
  
public class StatementTemplate implements Serializable {  
	private static final long serialVersionUID = 6189098499341186955L;

	private Template template;  
      
    private TYPE type;  
      
    public StatementTemplate(TYPE type, Template template) {  
        this.template = template;  
        this.type = type;  
    }  
  
    public TYPE getType() {  
        return type;  
    }  
  
    public void setType(TYPE type) {  
        this.type = type;  
    }  
  
    public Template getTemplate() {  
        return template;  
    }  
  
    public void setTemplate(Template template) {  
        this.template = template;  
    }  
  
    public static enum TYPE {  
        HQL,SQL  
    }  
      
      
}  