package leetcode.stackandpq;

import java.util.ArrayList;
import java.util.Stack;

/**
 * LeetCode 71
 * Given an absolute path for a file (Unix-style), simplify it.
	For example,
	path = "/home/", => "/home"
	path = "/a/./b/../../c/", => "/c"
	先将字符串依"/"分割出来，然后检查每个分割出来的字符串。
	当字符串为空或者为"."，不做任何操作。
	当字符串不为".."，则表明有效路径，要加入list。
	当字符串为"..", 则（返回上级目录）删除list最后元素（在不空的情况下）。
 */
public class SimplifyPath {
	
	public static String simplifyPath(String path) {
        String[] strs = path.split("/");
        ArrayList<String> result = new ArrayList<>();
        for(String s: strs){
        	if(!"".equals(s)){
        		if(s.equals("."))	continue;
        		if(s.equals(".."))	{
        			if(!result.isEmpty()) result.remove(result.size()-1);
        		}
        		else				result.add(s);
        	}
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        for(String s: result){
        	sb.append(s).append("/");
        }
        if(sb.length() >1) sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }


	public static String simplifyPath1(String path) {
		if(path == null || path.length() == 0)  return "/";
		Stack<String> stack = new Stack<>();
		String[] strs = path.split("/");
		for(String s: strs) {
			if (s.equals("..") && !stack.isEmpty()) stack.pop();
			else if(!s.equals("") && !s.equals(".") && !s.equals("..")) stack.push(s);
		}

		ArrayList<String> list = new ArrayList<>(stack);
		return "/" + String.join("/", list);
	}
	public static void main(String[] args) {
		String path = "/a/./b/../../c/";
		String path1 = "/home/";
		String path2 = "/..";
		System.out.println(simplifyPath(path2));
		System.out.println(simplifyPath1(path2));
	}
}
