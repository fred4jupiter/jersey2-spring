package com.mkyong.transaction.impl;

import java.util.Date;

import com.mkyong.transaction.TransactionBo;

public class TransactionBoImpl implements TransactionBo {

  @Override
  public String save() {
    return "Jersey + Spring example. time: " + new Date();
  }

}