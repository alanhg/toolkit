package com.qq.reader.filebrowser;

import com.google.zxing.common.StringUtils;
import com.qq.reader.common.monitor.f;
import com.tencent.android.tpush.common.MessageKey;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: PinyinAll */
public class c {
    private static Map<String, Integer> a;
    private static Map<Character, String> b;

    static {
        a = null;
        b = null;
        if (a == null) {
            a = Collections.synchronizedMap(new LinkedHashMap(396));
            b = Collections.synchronizedMap(new LinkedHashMap(200));
        }
        a();
        b();
        f.d("PinyinAll", "LetterParser inial.");
    }

    public static void a() {
        a.put("’a", Integer.valueOf(-20319));
        a.put("’ai", Integer.valueOf(-20317));
        a.put("’an", Integer.valueOf(-20304));
        a.put("’ang", Integer.valueOf(-20295));
        a.put("’ao", Integer.valueOf(-20292));
        a.put("ba", Integer.valueOf(-20283));
        a.put("bai", Integer.valueOf(-20265));
        a.put("ban", Integer.valueOf(-20257));
        a.put("bang", Integer.valueOf(-20242));
        a.put("bao", Integer.valueOf(-20230));
        a.put("bei", Integer.valueOf(-20051));
        a.put("ben", Integer.valueOf(-20036));
        a.put("beng", Integer.valueOf(-20032));
        a.put("bi", Integer.valueOf(-20026));
        a.put("bian", Integer.valueOf(-20002));
        a.put("biao", Integer.valueOf(-19990));
        a.put("bie", Integer.valueOf(-19986));
        a.put("bin", Integer.valueOf(-19982));
        a.put("bing", Integer.valueOf(-19976));
        a.put("bo", Integer.valueOf(-19805));
        a.put("bu", Integer.valueOf(-19784));
        a.put("ca", Integer.valueOf(-19775));
        a.put("cai", Integer.valueOf(-19774));
        a.put("can", Integer.valueOf(-19763));
        a.put("cang", Integer.valueOf(-19756));
        a.put("cao", Integer.valueOf(-19751));
        a.put("ce", Integer.valueOf(-19746));
        a.put("ceng", Integer.valueOf(-19741));
        a.put("cha", Integer.valueOf(-19739));
        a.put("chai", Integer.valueOf(-19728));
        a.put("chan", Integer.valueOf(-19725));
        a.put("chang", Integer.valueOf(-19715));
        a.put("chao", Integer.valueOf(-19540));
        a.put("che", Integer.valueOf(-19531));
        a.put("chen", Integer.valueOf(-19525));
        a.put("cheng", Integer.valueOf(-19515));
        a.put("chi", Integer.valueOf(-19500));
        a.put("chong", Integer.valueOf(-19484));
        a.put("chou", Integer.valueOf(-19479));
        a.put("chu", Integer.valueOf(-19467));
        a.put("chuai", Integer.valueOf(-19289));
        a.put("chuan", Integer.valueOf(-19288));
        a.put("chuang", Integer.valueOf(-19281));
        a.put("chui", Integer.valueOf(-19275));
        a.put("chun", Integer.valueOf(-19270));
        a.put("chuo", Integer.valueOf(-19263));
        a.put("ci", Integer.valueOf(-19261));
        a.put("cong", Integer.valueOf(-19249));
        a.put("cou", Integer.valueOf(-19243));
        a.put("cu", Integer.valueOf(-19242));
        a.put("cuan", Integer.valueOf(-19238));
        a.put("cui", Integer.valueOf(-19235));
        a.put("cun", Integer.valueOf(-19227));
        a.put("cuo", Integer.valueOf(-19224));
        a.put("da", Integer.valueOf(-19218));
        a.put("dai", Integer.valueOf(-19212));
        a.put("dan", Integer.valueOf(-19038));
        a.put("dang", Integer.valueOf(-19023));
        a.put("dao", Integer.valueOf(-19018));
        a.put("de", Integer.valueOf(-19006));
        a.put("deng", Integer.valueOf(-19003));
        a.put("di", Integer.valueOf(-18996));
        a.put("dian", Integer.valueOf(-18977));
        a.put("diao", Integer.valueOf(-18961));
        a.put("die", Integer.valueOf(-18952));
        a.put("ding", Integer.valueOf(-18783));
        a.put("diu", Integer.valueOf(-18774));
        a.put("dong", Integer.valueOf(-18773));
        a.put("dou", Integer.valueOf(-18763));
        a.put("du", Integer.valueOf(-18756));
        a.put("duan", Integer.valueOf(-18741));
        a.put("dui", Integer.valueOf(-18735));
        a.put("dun", Integer.valueOf(-18731));
        a.put("duo", Integer.valueOf(-18722));
        a.put("’e", Integer.valueOf(-18710));
        a.put("’en", Integer.valueOf(-18697));
        a.put("’er", Integer.valueOf(-18696));
        a.put("fa", Integer.valueOf(-18526));
        a.put("fan", Integer.valueOf(-18518));
        a.put("fang", Integer.valueOf(-18501));
        a.put("fei", Integer.valueOf(-18490));
        a.put("fen", Integer.valueOf(-18478));
        a.put("feng", Integer.valueOf(-18463));
        a.put("fo", Integer.valueOf(-18448));
        a.put("fou", Integer.valueOf(-18447));
        a.put("fu", Integer.valueOf(-18446));
        a.put("ga", Integer.valueOf(-18239));
        a.put("gai", Integer.valueOf(-18237));
        a.put("gan", Integer.valueOf(-18231));
        a.put("gang", Integer.valueOf(-18220));
        a.put("gao", Integer.valueOf(-18211));
        a.put("ge", Integer.valueOf(-18201));
        a.put("gei", Integer.valueOf(-18184));
        a.put("gen", Integer.valueOf(-18183));
        a.put("geng", Integer.valueOf(-18181));
        a.put("gong", Integer.valueOf(-18012));
        a.put("gou", Integer.valueOf(-17997));
        a.put("gu", Integer.valueOf(-17988));
        a.put("gua", Integer.valueOf(-17970));
        a.put("guai", Integer.valueOf(-17964));
        a.put("guan", Integer.valueOf(-17961));
        a.put("guang", Integer.valueOf(-17950));
        a.put("gui", Integer.valueOf(-17947));
        a.put("gun", Integer.valueOf(-17931));
        a.put("guo", Integer.valueOf(-17928));
        a.put("ha", Integer.valueOf(-17922));
        a.put("hai", Integer.valueOf(-17759));
        a.put("han", Integer.valueOf(-17752));
        a.put("hang", Integer.valueOf(-17733));
        a.put("hao", Integer.valueOf(-17730));
        a.put("he", Integer.valueOf(-17721));
        a.put("hei", Integer.valueOf(-17703));
        a.put("hen", Integer.valueOf(-17701));
        a.put("heng", Integer.valueOf(-17697));
        a.put("hong", Integer.valueOf(-17692));
        a.put("hou", Integer.valueOf(-17683));
        a.put("hu", Integer.valueOf(-17676));
        a.put("hua", Integer.valueOf(-17496));
        a.put("huai", Integer.valueOf(-17487));
        a.put("huan", Integer.valueOf(-17482));
        a.put("huang", Integer.valueOf(-17468));
        a.put("hui", Integer.valueOf(-17454));
        a.put("hun", Integer.valueOf(-17433));
        a.put("huo", Integer.valueOf(-17427));
        a.put("ji", Integer.valueOf(-17417));
        a.put("jia", Integer.valueOf(-17202));
        a.put("jian", Integer.valueOf(-17185));
        a.put("jiang", Integer.valueOf(-16983));
        a.put("jiao", Integer.valueOf(-16970));
        a.put("jie", Integer.valueOf(-16942));
        a.put("jin", Integer.valueOf(-16915));
        a.put("jing", Integer.valueOf(-16733));
        a.put("jiong", Integer.valueOf(-16708));
        a.put("jiu", Integer.valueOf(-16706));
        a.put("ju", Integer.valueOf(-16689));
        a.put("juan", Integer.valueOf(-16664));
        a.put("jue", Integer.valueOf(-16657));
        a.put("jun", Integer.valueOf(-16647));
        a.put("ka", Integer.valueOf(-16474));
        a.put("kai", Integer.valueOf(-16470));
        a.put("kan", Integer.valueOf(-16465));
        a.put("kang", Integer.valueOf(-16459));
        a.put("kao", Integer.valueOf(-16452));
        a.put("ke", Integer.valueOf(-16448));
        a.put("ken", Integer.valueOf(-16433));
        a.put("keng", Integer.valueOf(-16429));
        a.put("kong", Integer.valueOf(-16427));
        a.put("kou", Integer.valueOf(-16423));
        a.put("ku", Integer.valueOf(-16419));
        a.put("kua", Integer.valueOf(-16412));
        a.put("kuai", Integer.valueOf(-16407));
        a.put("kuan", Integer.valueOf(-16403));
        a.put("kuang", Integer.valueOf(-16401));
        a.put("kui", Integer.valueOf(-16393));
        a.put("kun", Integer.valueOf(-16220));
        a.put("kuo", Integer.valueOf(-16216));
        a.put("la", Integer.valueOf(-16212));
        a.put("lai", Integer.valueOf(-16205));
        a.put("lan", Integer.valueOf(-16202));
        a.put("lang", Integer.valueOf(-16187));
        a.put("lao", Integer.valueOf(-16180));
        a.put("le", Integer.valueOf(-16171));
        a.put("lei", Integer.valueOf(-16169));
        a.put("leng", Integer.valueOf(-16158));
        a.put("li", Integer.valueOf(-16155));
        a.put("lia", Integer.valueOf(-15959));
        a.put("lian", Integer.valueOf(-15958));
        a.put("liang", Integer.valueOf(-15944));
        a.put("liao", Integer.valueOf(-15933));
        a.put("lie", Integer.valueOf(-15920));
        a.put("lin", Integer.valueOf(-15915));
        a.put("ling", Integer.valueOf(-15903));
        a.put("liu", Integer.valueOf(-15889));
        a.put("long", Integer.valueOf(-15878));
        a.put("lou", Integer.valueOf(-15707));
        a.put("lu", Integer.valueOf(-15701));
        a.put("lv", Integer.valueOf(-15681));
        a.put("luan", Integer.valueOf(-15667));
        a.put("lue", Integer.valueOf(-15661));
        a.put("lun", Integer.valueOf(-15659));
        a.put("luo", Integer.valueOf(-15652));
        a.put("ma", Integer.valueOf(-15640));
        a.put("mai", Integer.valueOf(-15631));
        a.put("man", Integer.valueOf(-15625));
        a.put("mang", Integer.valueOf(-15454));
        a.put("mao", Integer.valueOf(-15448));
        a.put("me", Integer.valueOf(-15436));
        a.put("mei", Integer.valueOf(-15435));
        a.put("men", Integer.valueOf(-15419));
        a.put("meng", Integer.valueOf(-15416));
        a.put("mi", Integer.valueOf(-15408));
        a.put("mian", Integer.valueOf(-15394));
        a.put("miao", Integer.valueOf(-15385));
        a.put("mie", Integer.valueOf(-15377));
        a.put(MessageKey.MSG_ACCEPT_TIME_MIN, Integer.valueOf(-15375));
        a.put("ming", Integer.valueOf(-15369));
        a.put("miu", Integer.valueOf(-15363));
        a.put("mo", Integer.valueOf(-15362));
        a.put("mou", Integer.valueOf(-15183));
        a.put("mu", Integer.valueOf(-15180));
        a.put("na", Integer.valueOf(-15165));
        a.put("nai", Integer.valueOf(-15158));
        a.put("nan", Integer.valueOf(-15153));
        a.put("nang", Integer.valueOf(-15150));
        a.put("nao", Integer.valueOf(-15149));
        a.put("ne", Integer.valueOf(-15144));
        a.put("nei", Integer.valueOf(-15143));
        a.put("nen", Integer.valueOf(-15141));
        a.put("neng", Integer.valueOf(-15140));
        a.put("ni", Integer.valueOf(-15139));
        a.put("nian", Integer.valueOf(-15128));
        a.put("niang", Integer.valueOf(-15121));
        a.put("niao", Integer.valueOf(-15119));
        a.put("nie", Integer.valueOf(-15117));
        a.put("nin", Integer.valueOf(-15110));
        a.put("ning", Integer.valueOf(-15109));
        a.put("niu", Integer.valueOf(-14941));
        a.put("nong", Integer.valueOf(-14937));
        a.put("nu", Integer.valueOf(-14933));
        a.put("nv", Integer.valueOf(-14930));
        a.put("nuan", Integer.valueOf(-14929));
        a.put("nue", Integer.valueOf(-14928));
        a.put("nuo", Integer.valueOf(-14926));
        a.put("’o", Integer.valueOf(-14922));
        a.put("’ou", Integer.valueOf(-14921));
        a.put("pa", Integer.valueOf(-14914));
        a.put("pai", Integer.valueOf(-14908));
        a.put("pan", Integer.valueOf(-14902));
        a.put("pang", Integer.valueOf(-14894));
        a.put("pao", Integer.valueOf(-14889));
        a.put("pei", Integer.valueOf(-14882));
        a.put("pen", Integer.valueOf(-14873));
        a.put("peng", Integer.valueOf(-14871));
        a.put("pi", Integer.valueOf(-14857));
        a.put("pian", Integer.valueOf(-14678));
        a.put("piao", Integer.valueOf(-14674));
        a.put("pie", Integer.valueOf(-14670));
        a.put("pin", Integer.valueOf(-14668));
        a.put("ping", Integer.valueOf(-14663));
        a.put("po", Integer.valueOf(-14654));
        a.put("pu", Integer.valueOf(-14645));
        a.put("qi", Integer.valueOf(-14630));
        a.put("qia", Integer.valueOf(-14594));
        a.put("qian", Integer.valueOf(-14429));
        a.put("qiang", Integer.valueOf(-14407));
        a.put("qiao", Integer.valueOf(-14399));
        a.put("qie", Integer.valueOf(-14384));
        a.put("qin", Integer.valueOf(-14379));
        a.put("qing", Integer.valueOf(-14368));
        a.put("qiong", Integer.valueOf(-14355));
        a.put("qiu", Integer.valueOf(-14353));
        a.put("qu", Integer.valueOf(-14345));
        a.put("quan", Integer.valueOf(-14170));
        a.put("que", Integer.valueOf(-14159));
        a.put("qun", Integer.valueOf(-14151));
        a.put("ran", Integer.valueOf(-14149));
        a.put("rang", Integer.valueOf(-14145));
        a.put("rao", Integer.valueOf(-14140));
        a.put("re", Integer.valueOf(-14137));
        a.put("ren", Integer.valueOf(-14135));
        a.put("reng", Integer.valueOf(-14125));
        a.put("ri", Integer.valueOf(-14123));
        a.put("rong", Integer.valueOf(-14122));
        a.put("rou", Integer.valueOf(-14112));
        a.put("ru", Integer.valueOf(-14109));
        a.put("ruan", Integer.valueOf(-14099));
        a.put("rui", Integer.valueOf(-14097));
        a.put("run", Integer.valueOf(-14094));
        a.put("ruo", Integer.valueOf(-14092));
        a.put("sa", Integer.valueOf(-14090));
        a.put("sai", Integer.valueOf(-14087));
        a.put("san", Integer.valueOf(-14083));
        a.put("sang", Integer.valueOf(-13917));
        a.put("sao", Integer.valueOf(-13914));
        a.put("se", Integer.valueOf(-13910));
        a.put("sen", Integer.valueOf(-13907));
        a.put("seng", Integer.valueOf(-13906));
        a.put("sha", Integer.valueOf(-13905));
        a.put("shai", Integer.valueOf(-13896));
        a.put("shan", Integer.valueOf(-13894));
        a.put("shang", Integer.valueOf(-13878));
        a.put("shao", Integer.valueOf(-13870));
        a.put("she", Integer.valueOf(-13859));
        a.put("shen", Integer.valueOf(-13847));
        a.put("sheng", Integer.valueOf(-13831));
        a.put("shi", Integer.valueOf(-13658));
        a.put("shou", Integer.valueOf(-13611));
        a.put("shu", Integer.valueOf(-13601));
        a.put("shua", Integer.valueOf(-13406));
        a.put("shuai", Integer.valueOf(-13404));
        a.put("shuan", Integer.valueOf(-13400));
        a.put("shuang", Integer.valueOf(-13398));
        a.put("shui", Integer.valueOf(-13395));
        a.put("shun", Integer.valueOf(-13391));
        a.put("shuo", Integer.valueOf(-13387));
        a.put("si", Integer.valueOf(-13383));
        a.put("song", Integer.valueOf(-13367));
        a.put("sou", Integer.valueOf(-13359));
        a.put("su", Integer.valueOf(-13356));
        a.put("suan", Integer.valueOf(-13343));
        a.put("sui", Integer.valueOf(-13340));
        a.put("sun", Integer.valueOf(-13329));
        a.put("suo", Integer.valueOf(-13326));
        a.put("ta", Integer.valueOf(-13318));
        a.put("tai", Integer.valueOf(-13147));
        a.put("tan", Integer.valueOf(-13138));
        a.put("tang", Integer.valueOf(-13120));
        a.put("tao", Integer.valueOf(-13107));
        a.put("te", Integer.valueOf(-13096));
        a.put("teng", Integer.valueOf(-13095));
        a.put("ti", Integer.valueOf(-13091));
        a.put("tian", Integer.valueOf(-13076));
        a.put("tiao", Integer.valueOf(-13068));
        a.put("tie", Integer.valueOf(-13063));
        a.put("ting", Integer.valueOf(-13060));
        a.put("tong", Integer.valueOf(-12888));
        a.put("tou", Integer.valueOf(-12875));
        a.put("tu", Integer.valueOf(-12871));
        a.put("tuan", Integer.valueOf(-12860));
        a.put("tui", Integer.valueOf(-12858));
        a.put("tun", Integer.valueOf(-12852));
        a.put("tuo", Integer.valueOf(-12849));
        a.put("wa", Integer.valueOf(-12838));
        a.put("wai", Integer.valueOf(-12831));
        a.put("wan", Integer.valueOf(-12829));
        a.put("wang", Integer.valueOf(-12812));
        a.put("wei", Integer.valueOf(-12802));
        a.put("wen", Integer.valueOf(-12607));
        a.put("weng", Integer.valueOf(-12597));
        a.put("wo", Integer.valueOf(-12594));
        a.put("wu", Integer.valueOf(-12585));
        a.put("xi", Integer.valueOf(-12556));
        a.put("xia", Integer.valueOf(-12359));
        a.put("xian", Integer.valueOf(-12346));
        a.put("xiang", Integer.valueOf(-12320));
        a.put("xiao", Integer.valueOf(-12300));
        a.put("xie", Integer.valueOf(-12120));
        a.put("xin", Integer.valueOf(-12099));
        a.put("xing", Integer.valueOf(-12089));
        a.put("xiong", Integer.valueOf(-12074));
        a.put("xiu", Integer.valueOf(-12067));
        a.put("xu", Integer.valueOf(-12058));
        a.put("xuan", Integer.valueOf(-12039));
        a.put("xue", Integer.valueOf(-11867));
        a.put("xun", Integer.valueOf(-11861));
        a.put("ya", Integer.valueOf(-11847));
        a.put("yan", Integer.valueOf(-11831));
        a.put("yang", Integer.valueOf(-11798));
        a.put("yao", Integer.valueOf(-11781));
        a.put("ye", Integer.valueOf(-11604));
        a.put("yi", Integer.valueOf(-11589));
        a.put("yin", Integer.valueOf(-11536));
        a.put("ying", Integer.valueOf(-11358));
        a.put("yo", Integer.valueOf(-11340));
        a.put("yong", Integer.valueOf(-11339));
        a.put("you", Integer.valueOf(-11324));
        a.put("yu", Integer.valueOf(-11303));
        a.put("yuan", Integer.valueOf(-11097));
        a.put("yue", Integer.valueOf(-11077));
        a.put("yun", Integer.valueOf(-11067));
        a.put("za", Integer.valueOf(-11055));
        a.put("zai", Integer.valueOf(-11052));
        a.put("zan", Integer.valueOf(-11045));
        a.put("zang", Integer.valueOf(-11041));
        a.put("zao", Integer.valueOf(-11038));
        a.put("ze", Integer.valueOf(-11024));
        a.put("zei", Integer.valueOf(-11020));
        a.put("zen", Integer.valueOf(-11019));
        a.put("zeng", Integer.valueOf(-11018));
        a.put("zha", Integer.valueOf(-11014));
        a.put("zhai", Integer.valueOf(-10838));
        a.put("zhan", Integer.valueOf(-10832));
        a.put("zhang", Integer.valueOf(-10815));
        a.put("zhao", Integer.valueOf(-10800));
        a.put("zhe", Integer.valueOf(-10790));
        a.put("zhen", Integer.valueOf(-10780));
        a.put("zheng", Integer.valueOf(-10764));
        a.put("zhi", Integer.valueOf(-10587));
        a.put("zhong", Integer.valueOf(-10544));
        a.put("zhou", Integer.valueOf(-10533));
        a.put("zhu", Integer.valueOf(-10519));
        a.put("zhua", Integer.valueOf(-10331));
        a.put("zhuai", Integer.valueOf(-10329));
        a.put("zhuan", Integer.valueOf(-10328));
        a.put("zhuang", Integer.valueOf(-10322));
        a.put("zhui", Integer.valueOf(-10315));
        a.put("zhun", Integer.valueOf(-10309));
        a.put("zhuo", Integer.valueOf(-10307));
        a.put("zi", Integer.valueOf(-10296));
        a.put("zong", Integer.valueOf(-10281));
        a.put("zou", Integer.valueOf(-10274));
        a.put("zu", Integer.valueOf(-10270));
        a.put("zuan", Integer.valueOf(-10262));
        a.put("zui", Integer.valueOf(-10260));
        a.put("zun", Integer.valueOf(-10256));
        a.put("zuo", Integer.valueOf(-10254));
    }

    public static void a(char c, String str) {
        b.put(Character.valueOf(c), str);
    }

    public static void b() {
        a('奡', "ao");
        a('灞', "ba");
        a('犇', "ben");
        a('猋', "biao");
        a('骉', "biao");
        a('杈', "cha");
        a('棽', "chen");
        a('琤', "cheng");
        a('魑', "chi");
        a('虫', "chong");
        a('翀', "chong");
        a('麤', "cu");
        a('毳', "cui");
        a('昉', "fang");
        a('沣', "feng");
        a('玽', "gou");
        a('焓', "han");
        a('琀', "han");
        a('晗', "han");
        a('浛', "han");
        a('翮', "he");
        a('翯', "he");
        a('嬛', "huan");
        a('翙', "hui");
        a('劼', "jie");
        a('璟', "jing");
        a('誩', "jing");
        a('竞', "jing");
        a('焜', "kun");
        a('琨', "kun");
        a('鹍', "kun");
        a('骊', "li");
        a('鎏', "liu");
        a('嫚', "man");
        a('槑', "mei");
        a('淼', "miao");
        a('婻', "nan");
        a('暔', "nan");
        a('甯', "ning");
        a('寗', "ning");
        a('掱', "pa");
        a('玭', "pi");
        a('汧', "qian");
        a('骎', "qin");
        a('甠', "qing");
        a('暒', "qing");
        a('凊', "qing");
        a('郬', "qing");
        a('靘', "qing");
        a('悫', "que");
        a('慤', "que");
        a('瑢', "rong");
        a('珅', "shen");
        a('屾', "shen");
        a('燊', "shen");
        a('焺', "sheng");
        a('珄', "sheng");
        a('晟', "sheng");
        a('昇', "sheng");
        a('眚', "sheng");
        a('湦', "sheng");
        a('陹', "sheng");
        a('竔', "sheng");
        a('琞', "sheng");
        a('湜', "shi");
        a('苏', "su");
        a('弢', "tao");
        a('瑱', "tian");
        a('仝', "tong");
        a('烓', "wei");
        a('炜', "wei");
        a('玮', "wei");
        a('沕', "wu");
        a('邬', "wu");
        a('曦', "xi");
        a('顕', "xian");
        a('婋', "xiao");
        a('虓', "xiao");
        a('筱', "xiao");
        a('勰', "xie");
        a('忻', "xin");
        a('庥', "xiu");
        a('媭', "xu");
        a('珝', "xu");
        a('昫', "xu");
        a('烜', "xuan");
        a('煊', "xuan");
        a('翾', "xuan");
        a('昍', "xuan");
        a('暄', "xuan");
        a('娅', "ya");
        a('琰', "yan");
        a('妍', "yan");
        a('焱', "yan");
        a('玚', "yang");
        a('旸', "yang");
        a('飏', "yang");
        a('垚', "yao");
        a('峣', "yao");
        a('怡', "yi");
        a('燚', "yi");
        a('晹', "yi");
        a('祎', "yi");
        a('瑛', "ying");
        a('煐', "ying");
        a('媖', "ying");
        a('暎', "ying");
        a('滢', "ying");
        a('锳', "ying");
        a('莜', "you");
        a('昱', "yu");
        a('沄', "yun");
        a('晢', "zhe");
        a('喆', "zhe");
        a('臸', "zhi");
    }

    public static int a(char c) {
        byte[] bytes;
        try {
            bytes = String.valueOf(c).getBytes(StringUtils.GB2312);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bytes = null;
        }
        if (bytes == null || bytes.length == 0 || bytes.length > 2) {
            return 0;
        }
        if (bytes.length == 1) {
            return bytes[0];
        }
        if (bytes.length != 2) {
            return 0;
        }
        return (((bytes[0] + 256) * 256) + (bytes[1] + 256)) - 65536;
    }

    public static String a(int i) {
        if (i > 0 && i < 160) {
            return String.valueOf((char) i);
        }
        if (i < -20319 || i > -10247) {
            return "";
        }
        int i2 = -20319;
        String str = null;
        for (String str2 : a.keySet()) {
            Integer num = (Integer) a.get(str2);
            if (num != null) {
                if (i < i2 || i >= num.intValue()) {
                    i2 = num.intValue();
                    str = str2;
                } else if (str != null) {
                    return str;
                } else {
                    return str2;
                }
            }
        }
        return "";
    }

    public static String a(String str, boolean z) {
        if (str == null) {
            return "";
        }
        char[] toCharArray = str.trim().toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        int length = toCharArray.length;
        for (int i = 0; i < length; i++) {
            int a = a(toCharArray[i]);
            if (a == 0) {
                stringBuffer.append(toCharArray[i]);
            } else {
                String a2 = a(a);
                if (a2 == null || a2.length() == 0) {
                    a2 = (String) b.get(Character.valueOf(toCharArray[i]));
                } else if (z) {
                    stringBuffer.append(a2.startsWith("’") ? a2.substring(1, 2) : a2.substring(0, 1));
                } else {
                    stringBuffer.append(a2);
                }
            }
        }
        return stringBuffer.toString();
    }
}
