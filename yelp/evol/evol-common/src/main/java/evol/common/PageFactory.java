package evol.common;

import sun.jvm.hotspot.debugger.Page;

public abstract class PageFactory {

    public static Page all(){ return build(0, Integer.MAX_VALUE);  }

    public static Page forList(int page){  return build(page -1, 10); }

    public static Page forDashboard(int page){  return build(page -1, 10);  }

    public static Page forFeed(int page){  return build(page -1, 20);  }

    public static Page forSearch(int page){ return build(page -1, 20);   }

    private static Page build(int page, int pageSize){
        return null;

    }
}
