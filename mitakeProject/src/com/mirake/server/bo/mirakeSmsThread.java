package com.mirake.server.bo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.mirake.PhoneNewsletter;

/**
 * 
 * @author IMI-JAVA-Ryan 此為傳送至客戶手機訊號的多執行續用法
 *
 */
public class mirakeSmsThread extends Thread {

	private HashMap<String, Object> batchArgsMaps;

	public void setBatchArgsMaps(HashMap<String, Object> batchArgsMaps) {
		this.batchArgsMaps = batchArgsMaps;
	}

	@Override
	public void run() {
		
		
		/*這裡去組成下單資訊*/
		
		String messageContents = "需要傳遞給客戶的訊息";
		String messageTransferNumber = "15445";
		
		
		//取得三竹資訊的證券
		HashMap<String, Object> mitakLoginVal = new HashMap<>();
		try {
			mitakLoginVal = mirakeBo.getMitakeSettingVal();
			PhoneNewsletter phletter = new PhoneNewsletter();
		
			//傳送三竹簡訊給客戶
			System.out.println("多執行續");
//			phletter.MT4oederSMSMessage(messageContents, messageTransferNumber , mitakLoginVal);
		
					
		} catch (SQLException e) {
			System.out.println("資料發生錯誤:" + e);
		}

	}

}