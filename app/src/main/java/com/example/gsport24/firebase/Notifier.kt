package com.example.gsport24.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.gsport24.R
import com.example.gsport24.domain.entities.CloudNotificationDomain
import com.example.gsport24.root.ext.getColorCompat

object Notifier {

	fun show(context : Context, notificationDomain : CloudNotificationDomain) {
		showNotification(context, notificationDomain)
	}

	private fun showNotification(context : Context, notificationDomain : CloudNotificationDomain) {
		createNotificationChannel(context)

		val not = NotificationCompat.Builder(context, "NotificationChannelId")
			.setSmallIcon(R.drawable.ic_logo)
			.setContentTitle(notificationDomain.title)
			.setColor(context.getColorCompat(R.color.dark_blue))
			.setPriority(NotificationCompat.PRIORITY_HIGH)
			.setVibrate(longArrayOf(500L, 500L, 500L, 500L))
//			.setContentIntent(createDeepLink(context,notificationType))
			.setContentText(notificationDomain.body)
			.build()
		NotificationManagerCompat.from(context).notify(74, not)
	}

	private fun createNotificationChannel(context : Context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val name = context.getString(R.string.app_name)
			val descriptionText = "NotificationChannelIdDescription"
			val importance = NotificationManager.IMPORTANCE_HIGH
			val channel = NotificationChannel("NotificationChannelId", name, importance).apply {
				description = descriptionText
			}
			val notificationManager : NotificationManager =
				context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
			notificationManager.createNotificationChannel(channel)
		}
	}

	/*private fun createDeepLink(context : Context,notificationType : BottomNavigationView.NavItem) : PendingIntent {
		val a = NavDeepLinkBuilder(context)
			.setGraph(if (notificationType == BottomNavigationView.NavItem.Notifications) R.navigation.nav_notifications else R.navigation.nav_home)
			.setDestination(*//*if (notificationType == BottomNavigationView.NavItem.Notifications) *//*R.id.notificationDetailsFragment *//*else R.id.newsDetailsFragment*//*)
			.createTaskStackBuilder()
		a.intents.forEachIndexed { index, _ ->
			a.editIntentAt(index)?.putExtra("notificationType", notificationType.ordinal)
		}
		return a.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)!!
	}*/

}