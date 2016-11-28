//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : ִ�л������������.java
//  @ Date : 2016/11/28
//  @ Author : 

package Lab7;

public class Simplify {
	private String input;
	String fun;
	private static final int MAXVARCOUNT = 200;
	private static int[] value = new int[MAXVARCOUNT];
	
	public Simplify(String input, String fun) {
		this.input = input;
		this.fun = fun;
		for (int i = 0; i < MAXVARCOUNT; i++) value[i] = 0; 
	}
	
	public String simplify() {
		OperateString ops = new OperateString();
		String newString = "";
		final String[] count = input.split(" "); 
		final int num = count.length;
		
		String[] var = new String[num - 1];
		for (int i = 1; i < num; i++) {
			var[i - 1] = ops.getVarStr(count[i], 0);
			final int len = count[i].length();
			final String n = count[i].substring(var[i - 1].length() + 1, len);
			final int v = Integer.parseInt(n);
			value[i - 1] = v;
		}

		String x = "";
		for (int i = 0; i < fun.length(); i++) {
			if (ops.isLetter(fun.charAt(i))) {
				x = ops.getVarStr(fun, i);
				boolean havevalue = false, havesquare = false;
				for (int j = 0; j < num - 1; j++) {
					if (x.equals(var[j])) {
						newString = newString + value[j];
						havevalue = true;
						break;
					} else if ((i + x.length()) < fun.length() 
							&& fun.charAt(i + x.length()) == '^') {
						final String l = ops.getNumStr(fun, i + x.length() + 1);
						i = i + 1 + l.length();
						newString = newString + x + '^' + l;
						havesquare = true;
					}
				}
				if (!havevalue && !havesquare) {
					newString = newString + x;
				}
				i = i + x.length() - 1;
			} else {
				newString = newString + fun.charAt(i);
			}
		}
		// System.out.println(newString);
		
		return newString;
	}
}
