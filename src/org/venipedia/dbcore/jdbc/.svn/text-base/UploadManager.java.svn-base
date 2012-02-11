package org.venipedia.dbcore.jdbc;

import java.net.MalformedURLException;
import java.util.ArrayList;

import net.sourceforge.jwbf.core.actions.util.ActionException;
import net.sourceforge.jwbf.core.actions.util.ProcessException;
import net.sourceforge.jwbf.core.contentRep.SimpleArticle;
import net.sourceforge.jwbf.mediawiki.actions.MediaWiki;
import net.sourceforge.jwbf.mediawiki.actions.editing.PostModifyContent;
import net.sourceforge.jwbf.mediawiki.actions.queries.AllPageTitles;
import net.sourceforge.jwbf.mediawiki.actions.util.VersionException;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

public class UploadManager {
	private MediaWikiBot bot;
	
	public UploadManager(String host, String user, String pass) throws MalformedURLException, ActionException{
		bot = new MediaWikiBot(host);
		bot.login(user, pass);
	}
	
	public void uploadArticle(String title, String content) throws ActionException, ProcessException{
		SimpleArticle article = new SimpleArticle(title);
		article.setText(content);
		article.setMinorEdit(false);
		PostModifyContent pmc = new PostModifyContent(bot, article);
		bot.performAction(pmc);
	}
	
	public String[] getTemplateNames(){
		ArrayList<String> names = new ArrayList<String>();
		try {
			AllPageTitles apt = new AllPageTitles(bot,MediaWiki.NS_TEMPLATE);
			for(String s:apt){
				names.add(s);
				System.out.println(s);
			}
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String[])names.toArray();
	}
	
	public static void main(String... args) throws Exception{
		UploadManager um = new UploadManager("http://www.venipedia.org/","ve11-maint-bot","100institute");
		um.getTemplateNames();
	}
}
