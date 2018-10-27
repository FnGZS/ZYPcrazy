 package com.crazyBird.controller.user.model;
 
 import com.crazyBird.controller.base.AbstractFlagModel;
 
 public class BindingModel extends AbstractFlagModel
 {
   private Integer result;
   private String asToken;
   
   public Integer getResult()
   {
     return this.result;
   }
   
   public void setResult(Integer result) {
     this.result = result;
   }
   
   public String getAsToken() {
     return this.asToken;
   }
   
   public void setAsToken(String asToken) {
     this.asToken = asToken;
   }
 }
