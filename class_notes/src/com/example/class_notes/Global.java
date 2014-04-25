package com.example.class_notes;

public class Global {
	public static class ListItem{
		public String description;
		public String title;
		public ListItem(String title,String description){
			this.title = title;
			this.description = description;
		}
		@Override
		public String toString(){
			return title;
		}
	}
	public static ListItem[] rsslist = new ListItem[]{
		new ListItem("DalekIpsum 1","MY VISION IS IMPAIRED! Spoilers! Reverse the polarity of the positron flow puny human Reverse the polarity of the positron flow Davros The socks with holes, dummy! You are better at dying I hereby invoke The Shadow Proclamation!"),
		new ListItem("DalekIpsum 2","Raxacoricofallapatorius EXTERMINATE! Allons-y, Alonso! Hey, who turned out the lights? Are you my mummy? DON'T BLINK! Geronimo EXTERMINATE! Galifrey MY VISION IS IMPAIRED! The Silence is Coming! Rose Tyler Donna Noble has left the library. Donna Noble has been saved. The Silence is Coming! Hey, who turned out the lights? EXTERMINATE! Spoilers! The Supreme Dalek Hello, Captain Jack Harkness"),
		new ListItem("DalekIpsum 3","The Master It's bigger on the inside! The Shadow Proclamation Hello, Captain Jack Harkness.  It's bigger on the inside! Hello sweetie Tick tock goes the clock... EXTERMINATE! We are Dalek puny human Rude and not ginger Captain Jack Harkness Spoilers!"),
		new ListItem("DalekIpsum 4","Galifreyan puny human Hello sweetie! EXTERMINATE! you are not alone in the universe Hello Sweetie. EXTERMINATE!")
	};
}
