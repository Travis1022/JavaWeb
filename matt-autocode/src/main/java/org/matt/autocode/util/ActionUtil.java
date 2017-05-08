package org.matt.autocode.util;

public class ActionUtil {
	
    private static String actionSuffix = "Action";
	public static String build(String className,boolean lowerCase,String separator)
	  {
	    String actionName = className;

	    if (actionName.equals(actionSuffix)) {
	    	throw new IllegalStateException("The action name cannot be the same as the action suffix [" + actionSuffix + "]");
	    }

	    if (actionName.endsWith(actionSuffix)) {
	    	actionName = actionName.substring(0, actionName.length() - actionSuffix.length());
	    }

	    char[] ca = actionName.toCharArray();
	    StringBuilder build = new StringBuilder("" + ca[0]);
	    boolean lower = true;
	    for (int i = 1; i < ca.length; i++) {
	      char c = ca[i];
	      if ((Character.isUpperCase(c)) && (lower)) {
	        build.append(separator);
	        lower = false;
	      } else if (!Character.isUpperCase(c)) {
	        lower = true;
	      }

	      build.append(c);
	    }

	    actionName = build.toString();
	    if (lowerCase) {
	      actionName = actionName.toLowerCase();
	    }
	    return actionName;
	  }
	
	public static void main(String[]args){
		System.out.println(ActionUtil.build("baseAction", true, "-"));
	}

}
