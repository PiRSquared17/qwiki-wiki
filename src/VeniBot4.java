import java.util.Map;

import net.sourceforge.jwbf.core.contentRep.SimpleArticle;
import net.sourceforge.jwbf.mediawiki.actions.meta.Siteinfo;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

public class VeniBot4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MediaWikiBot bot = new MediaWikiBot("http://www.venipedia.org/");
			bot.login("Ve11-maint-bot", "100institute");
			
			Siteinfo si=bot.getSiteinfo();
			Map<Integer, String> map = si.getNamespaces();
			for(int n:si.getNamespacesArray()){
				String namespace = map.get(n);
				System.out.println(namespace);
			}
			
			SimpleArticle article = new SimpleArticle(bot.readContent("Ve11-maint-bot"));
			article.addText("\n\n==Appending Text==\nWhen the bot loads the page off of the wiki instead" +
					" of starting from scratch, it is possible to add on to the end of an article " +
					"rather than wipe it clean and entirely replace its contents.");
			article.setMinorEdit(false);
			
//			PostModifyContent pmc = new PostModifyContent(bot, article);
//			
//			bot.performAction(pmc);
			

			SimpleArticle nav = new SimpleArticle(bot.readContent("Template:StreetTypeNav"));
			System.out.println(nav.getText());
			
//			SimpleArticle sa = new SimpleArticle(bot.readContent("Main Page"));
//			//System.out.println(sa.getText());
//			AllPageTitles apt = new AllPageTitles(bot, MediaWiki.NS_MAIN);
//			for (String articleName : apt) {
//			   //System.out.println(articleName);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
