package com.cathay.interview.batch;

import org.springframework.batch.item.ItemProcessor;

import com.cathay.interview.bean.ForexTransaction;

public class ForexTransactionItemProcessor implements ItemProcessor<ForexTransaction, ForexTransaction> {
	@Override
	public ForexTransaction process(final ForexTransaction forexTransaction) throws Exception {
		return forexTransaction;
	}
}