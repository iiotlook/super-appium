package com.virjar.superappium.xpath.function;

import android.util.Log;

import com.virjar.superappium.util.Constants;
import com.virjar.superappium.xpath.function.axis.AncestorFunction;
import com.virjar.superappium.xpath.function.axis.AncestorOrSelfFunction;
import com.virjar.superappium.xpath.function.axis.AxisFunction;
import com.virjar.superappium.xpath.function.axis.ChildFunction;
import com.virjar.superappium.xpath.function.axis.DescendantFunction;
import com.virjar.superappium.xpath.function.axis.DescendantOrSelfFunction;
import com.virjar.superappium.xpath.function.axis.FollowingSiblingFunction;
import com.virjar.superappium.xpath.function.axis.FollowingSiblingOneFunction;
import com.virjar.superappium.xpath.function.axis.ParentFunction;
import com.virjar.superappium.xpath.function.axis.PrecedingSiblingFunction;
import com.virjar.superappium.xpath.function.axis.SelfFunction;
import com.virjar.superappium.xpath.function.axis.SiblingFunction;
import com.virjar.superappium.xpath.function.filter.AbsFunction;
import com.virjar.superappium.xpath.function.filter.BooleanFunction;
import com.virjar.superappium.xpath.function.filter.ConcatFunction;
import com.virjar.superappium.xpath.function.filter.FalseFunction;
import com.virjar.superappium.xpath.function.filter.FilterFunction;
import com.virjar.superappium.xpath.function.filter.FirstFunction;
import com.virjar.superappium.xpath.function.filter.LastFunction;
import com.virjar.superappium.xpath.function.filter.LowerCaseFunction;
import com.virjar.superappium.xpath.function.filter.MatchesFunction;
import com.virjar.superappium.xpath.function.filter.NameFunction;
import com.virjar.superappium.xpath.function.filter.NotFunction;
import com.virjar.superappium.xpath.function.filter.NullToDefaultFunction;
import com.virjar.superappium.xpath.function.filter.PositionFunction;
import com.virjar.superappium.xpath.function.filter.PredicateFunction;
import com.virjar.superappium.xpath.function.filter.RootFunction;
import com.virjar.superappium.xpath.function.filter.StringFunction;
import com.virjar.superappium.xpath.function.filter.StringLengthFunction;
import com.virjar.superappium.xpath.function.filter.SubstringFunction;
import com.virjar.superappium.xpath.function.filter.TextFunction;
import com.virjar.superappium.xpath.function.filter.ToDoubleFunction;
import com.virjar.superappium.xpath.function.filter.ToIntFunction;
import com.virjar.superappium.xpath.function.filter.TrueFunction;
import com.virjar.superappium.xpath.function.filter.TryExeptionFunction;
import com.virjar.superappium.xpath.function.filter.UpperCaseFunction;
import com.virjar.superappium.xpath.function.select.AttrFunction;
import com.virjar.superappium.xpath.function.select.SelectFunction;
import com.virjar.superappium.xpath.function.select.TagSelectFunction;

import java.util.HashMap;
import java.util.Map;

public class FunctionEnv {
    private static Map<String, SelectFunction> selectFunctions = new HashMap<>();
    private static Map<String, FilterFunction> filterFunctions = new HashMap<>();
    private static Map<String, AxisFunction> axisFunctions = new HashMap<>();

    static {
        registerAllSelectFunctions();
        registerAllFilterFunctions();
        registerAllAxisFunctions();
    }

    public static SelectFunction getSelectFunction(String functionName) {
        return selectFunctions.get(functionName);
    }

    public static FilterFunction getFilterFunction(String functionName) {
        return filterFunctions.get(functionName);
    }

    public static AxisFunction getAxisFunction(String functionName) {
        return axisFunctions.get(functionName);
    }

    public synchronized static void registerSelectFunction(SelectFunction selectFunction) {
        if (selectFunctions.containsKey(selectFunction.getName())) {
            Log.w(Constants.TAG, "register a duplicate  select function " + selectFunction.getName());
        }
        selectFunctions.put(selectFunction.getName(), selectFunction);
    }

    public synchronized static void registerFilterFunction(FilterFunction filterFunction) {
        if (filterFunctions.containsKey(filterFunction.getName())) {
            Log.w(Constants.TAG, "register a duplicate  filter function " + filterFunction.getName());
        }
        filterFunctions.put(filterFunction.getName(), filterFunction);
    }

    public synchronized static void registerAxisFunciton(AxisFunction axisFunction) {
        if (axisFunctions.containsKey(axisFunction.getName())) {
            Log.w(Constants.TAG, "register a duplicate  axis function " + axisFunction.getName());
        }
        axisFunctions.put(axisFunction.getName(), axisFunction);
    }

    private static void registerAllSelectFunctions() {
        registerSelectFunction(new AttrFunction());
        registerSelectFunction(new com.virjar.superappium.xpath.function.select.SelfFunction());
        registerSelectFunction(new TagSelectFunction());
        registerSelectFunction(new com.virjar.superappium.xpath.function.select.TextFunction());
    }

    private static void registerAllFilterFunctions() {
        registerFilterFunction(new AbsFunction());
        registerFilterFunction(new BooleanFunction());
        registerFilterFunction(new ConcatFunction());
        registerFilterFunction(new FalseFunction());
        registerFilterFunction(new FirstFunction());
        registerFilterFunction(new LastFunction());
        registerFilterFunction(new LowerCaseFunction());
        registerFilterFunction(new MatchesFunction());
        registerFilterFunction(new NameFunction());
        registerFilterFunction(new NotFunction());
        registerFilterFunction(new NullToDefaultFunction());
        registerFilterFunction(new PositionFunction());
        registerFilterFunction(new PredicateFunction());
        registerFilterFunction(new RootFunction());
        registerFilterFunction(new StringFunction());
        registerFilterFunction(new StringLengthFunction());
        registerFilterFunction(new SubstringFunction());
        registerFilterFunction(new TextFunction());
        registerFilterFunction(new ToDoubleFunction());
        registerFilterFunction(new ToIntFunction());
        registerFilterFunction(new TrueFunction());
        registerFilterFunction(new TryExeptionFunction());
        registerFilterFunction(new UpperCaseFunction());
    }

    private static void registerAllAxisFunctions() {
        registerAxisFunciton(new AncestorFunction());
        registerAxisFunciton(new AncestorOrSelfFunction());
        registerAxisFunciton(new ChildFunction());
        registerAxisFunciton(new DescendantFunction());
        registerAxisFunciton(new DescendantOrSelfFunction());
        registerAxisFunciton(new FollowingSiblingFunction());
        registerAxisFunciton(new FollowingSiblingOneFunction());
        registerAxisFunciton(new ParentFunction());
        registerAxisFunciton(new PrecedingSiblingFunction());
        registerAxisFunciton(new SelfFunction());
        registerAxisFunciton(new SiblingFunction());
    }


}
