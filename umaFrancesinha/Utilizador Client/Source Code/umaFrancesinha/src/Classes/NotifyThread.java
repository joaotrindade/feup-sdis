package Classes;

import java.io.IOException;

import Graphics.NotificationFrame;
import umaFrancesinha.umaFrancesinha;


public class NotifyThread extends Thread {

	int temp;
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(umaFrancesinha.idUser!=-1)
			{
				try {
					umaFrancesinha.notificationlist.clear();
					if(umaFrancesinha.client.getNotifications() == true)
					{
						if(umaFrancesinha.wantNotifications==true)
						{
							NotificationFrame nf = new NotificationFrame();
							nf.showNotification(umaFrancesinha.notificationlist.size());
						}
						umaFrancesinha.notificationsPanel.printResults();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(umaFrancesinha.notificationTime*60*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	
	}
}
