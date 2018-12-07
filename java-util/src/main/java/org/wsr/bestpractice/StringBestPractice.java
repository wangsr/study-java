package org.wsr.bestpractice;

import com.google.common.base.*;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * 操作String的最佳实践
 *
 * @author wangsr
 * @date 2018/8/3
 */
public class StringBestPractice {

    public void split() {
        //[a,b,c]
        List<String> list = Splitter.on(",").trimResults().omitEmptyStrings().splitToList("a,b,c");
        //[abc,def,gh]
        list = Splitter.fixedLength(3).splitToList("abcdefgh");
    }

    public void joiner() {
        //1#2#3
        String str = Joiner.on("#").skipNulls().join(Arrays.asList(1, 2, 3));
    }

    /**
     * 字符匹配
     */
    public void charMatcher() {
        //[0-9]数字
        CharMatcher.inRange('0', '9').matchesAllOf("123");
        CharMatcher.javaDigit();
        //[a-z|A-Z]字母
        CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'));
        CharMatcher.javaLetter();
        //数字、字母
        CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).or(CharMatcher.inRange('0', '9'));
        CharMatcher.javaLetterOrDigit();
        //ascii
        CharMatcher.ascii();
    }

    /**
     * 获取字符集
     */
    public void charset() {
        //Charset.forName("UTF-8")
        Charset c = Charsets.UTF_8;
    }

    /**
     * 驼峰转换
     * Format	            Example
     * LOWER_CAMEL	        lowerCamel
     * LOWER_HYPHEN	        lower-hyphen
     * LOWER_UNDERSCORE	    lower_underscore
     * UPPER_CAMEL	        UpperCamel
     * UPPER_UNDERSCORE	    UPPER_UNDERSCORE
     */
    public void caseFormat() {
        //returns "constantName"
        CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");
    }

    /**
     * Apache common lang
     * StringUtils
     * <pre>
     *  IsEmpty/IsBlank - checks if a String contains text
     * 	Trim/Strip - removes leading and trailing whitespace
     * 	Equals/Compare - compares two strings null-safe
     * 	startsWith - check if a String starts with a prefix null-safe
     * 	endsWith - check if a String ends with a suffix null-safe
     * 	IndexOf/LastIndexOf/Contains - null-safe index-of checks
     * 	IndexOfAny/LastIndexOfAny/IndexOfAnyBut/LastIndexOfAnyBut - index-of any of a set of Strings
     * 	ContainsOnly/ContainsNone/ContainsAny - does String contains only/none/any of these characters
     * 	Substring/Left/Right/Mid - null-safe substring extractions
     * 	SubstringBefore/SubstringAfter/SubstringBetween - substring extraction relative to other strings
     * 	Split/Join - splits a String into an array of substrings and vice versa
     * 	Remove/Delete - removes part of a String
     * 	Replace/Overlay - Searches a String and replaces one String with another
     * 	Chomp/Chop - removes the last part of a String
     * 	AppendIfMissing - appends a suffix to the end of the String if not present
     * 	PrependIfMissing - prepends a prefix to the start of the String if not present
     * 	LeftPad/RightPad/Center/Repeat - pads a String
     * 	UpperCase/LowerCase/SwapCase/Capitalize/Uncapitalize - changes the case of a String
     * 	CountMatches - counts the number of occurrences of one String in another
     * 	IsAlpha/IsNumeric/IsWhitespace/IsAsciiPrintable - checks the characters in a String
     * 	DefaultString - protects against a null input String
     * 	Rotate - rotate (circular shift) a String
     * 	Reverse/ReverseDelimited - reverses a String
     * 	Abbreviate - abbreviates a string using ellipsis
     * 	Difference - compares Strings and reports on their differences
     * 	LevenshteinDistance - the number of changes needed to change one String into another
     * </pre>
     */
    public void strCommonOperation() {
        //Substring/Left/Right/Mid
        //SubstringBefore/SubstringAfter/SubstringBetween
        StringUtils.left("abc", 2);
        StringUtils.right("abc", 2);
        //AppendIfMissing
        //PrependIfMissing
        //LeftPad/RightPad/Center/Repeat - pads a String
        StringUtils.leftPad("1", 3, "0");
        StringUtils.rightPad("1", 3, "0");
        //UpperCase/LowerCase/SwapCase/Capitalize/Uncapitalize
        StringUtils.capitalize("abc");
        StringUtils.uncapitalize("abc");
        //Abbreviate(...)
        StringUtils.abbreviate("abcdef", 4);
    }
}
