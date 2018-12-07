package org.wsr.stu.observer.jdk_observer;

/**
 * Created by wangshengren on 2017/6/21.
 */
public class ObserverDemo {
    public static void main(String args[]) {
        TieDaoBuObservable h = new TieDaoBuObservable("温州火车出轨体现了我国高铁世界技术水平领先，和谐社会和谐号出事了");
        TieDaoBuObserver hpo1 = new TieDaoBuObserver("媒体A");
        TieDaoBuObserver hpo2 = new TieDaoBuObserver("屁民B");
        TieDaoBuObserver hpo3 = new TieDaoBuObserver("日本韩国嘲笑者C");
        h.addObserver(hpo1);
        h.addObserver(hpo2);
        h.addObserver(hpo3);

        h.setMessage("搜救结束，经生命探测仪发现没有生命迹象");// 修改官方消息

        h.setMessage("搜救结束了，还发现了一名2岁的女孩，真是奇迹");// 修改官方消息

        h.setMessage(
                "35一个神奇的数字,动车相撞35人死亡,河南平顶山矿难35人死亡,"
                        + "重庆暴雨35人死亡,云南大雨35人死亡。为什么死亡人数控制在36人以内？"
                        + "超过36人市委书记级别的要撤职，所以一开始发生就注定了死亡人数不会超过36。"
                        + "而事实上,我在查看国外报纸报道,华尔街日报说,"
                        + "这次动车事故,其中有47人死亡,200余人受伤"); // 修改官方消息
    }
}
