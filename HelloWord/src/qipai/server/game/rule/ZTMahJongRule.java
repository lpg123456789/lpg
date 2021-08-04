/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package qipai.server.game.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



*//**
	 * 昭通麻将算法
	 *
	 * @author zane
	 */
/*
public class ZTMahJongRule {

//初始牌范围
public static Map<Integer,Map<Integer,Integer>> BASE_PAIS = new HashMap<>();


//初始牌范围
public static Map<String,List<List<Integer>>> COMBINE_CACHE = new HashMap<>();

static {
	Map<Integer,Integer> data1 = new HashMap<>();
	for (int i = 11; i < 48; i++) {//取牌
		if (i % 10 == 0) {
			continue;
		}
		data1.put(i, i);	
	}
	BASE_PAIS.put(PaiType.WAN_TONG_TIAO_FENG_ZI, data1);
	
	Map<Integer,Integer> data2 = new HashMap<>();
	for (int i = 11; i < 48; i++){
		if (i % 10 == 0||i==41||i==42||i==43||i==44) {
			continue;
		}
		data2.put(i, i);				
	}
	BASE_PAIS.put(PaiType.WAN_TONG_TIAO_ZI, data2);
	
	Map<Integer,Integer> data3 = new HashMap<>();
	for (int i = 11; i < 45; i++){
		if (i % 10 == 0) {
			continue;
		}
		data3.put(i, i);		
	}
	BASE_PAIS.put(PaiType.WAN_TONG_TIAO_FENG, data3);
	
	Map<Integer,Integer> data4 = new HashMap<>();
	for (int i = 11; i < 40; i++){
		if (i % 10 == 0) {
			continue;
		}
		data4.put(i, i);		
	}
	BASE_PAIS.put(PaiType.WAN_TONG_TIAO, data4);
	
	
	
}


*//**
	 * 牌的类型
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static Map<Integer,Integer> getBasePais(int type){
return BASE_PAIS.get(type);
}


*//**
	 * 是否上家
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean isUpRole(int last,int seat){
if (last == 4) {
	if (seat == 1) {
		return true;
	}
}else if (seat - last == 1) {
	return true;
}
return false;
}

*//**
	 * 是否有顺刻
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static int chiNum(int lastPai,int[][] memberPai){
int num = 0;
int yu = lastPai / 10 - 1;
int mod = lastPai % 10;

if (mod == 9) {
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod - 2] >= 1) {
		num++;
	}
} else if (mod == 1) {
	if (memberPai[yu][mod + 1] >= 1 && memberPai[yu][mod + 2] >= 1) {
		num++;
	} 
} else if (mod == 8) {
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod - 2] >= 1) {
		num++;
	}
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod + 1] >= 1) {
		num++;
	}
} else if (mod == 2) {
	if (memberPai[yu][mod + 1] >= 1 && memberPai[yu][mod + 2] >= 1) {
		num++;
	}
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod + 1] >= 1) {
		num++;
	}
} else {
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod + 1] >= 1) {
		num++;
	}
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod - 2] >= 1) {
		num++;
	}
	if (memberPai[yu][mod + 1] >= 1 && memberPai[yu][mod + 2] >= 1) {
		num++;
	}
}
return num;
}

*//**
	 * 是否有顺刻
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static List<Integer> chiList(int lastPai,int[][] memberPai){
List<Integer> list = new ArrayList<>();
int yu = lastPai / 10 - 1;
int mod = lastPai % 10;

if (mod == 9) {
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod - 2] >= 1) {
		list.add(lastPai-2);
		list.add(lastPai-1);
		list.add(lastPai);
		return list;
	}
} else if (mod == 1) {
	if (memberPai[yu][mod + 1] >= 1 && memberPai[yu][mod + 2] >= 1) {
		list.add(lastPai);
		list.add(lastPai+1);
		list.add(lastPai+2);
		return list;
	} 
} else if (mod == 8) {
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod - 2] >= 1) {
		list.add(lastPai-2);
		list.add(lastPai-1);
		list.add(lastPai);
		return list;
	} else if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod + 1] >= 1) {
		list.add(lastPai-1);
		list.add(lastPai);
		list.add(lastPai+1);
		return list;
	}
} else if (mod == 2) {
	if (memberPai[yu][mod + 1] >= 1 && memberPai[yu][mod + 2] >= 1) {
		list.add(lastPai);
		list.add(lastPai+1);
		list.add(lastPai+2);
		return list;
	} else if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod + 1] >= 1) {
		list.add(lastPai-1);
		list.add(lastPai);
		list.add(lastPai+1);
		return list;
	}
} else {
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod + 1] >= 1) {
		list.add(lastPai-1);
		list.add(lastPai);
		list.add(lastPai+1);
		return list;
	} else if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod - 2] >= 1) {
		list.add(lastPai-2);
		list.add(lastPai-1);
		list.add(lastPai);
		return list;
	} else if (memberPai[yu][mod + 1] >= 1 && memberPai[yu][mod + 2] >= 1) {
		list.add(lastPai);
		list.add(lastPai+1);
		list.add(lastPai+2);
		return list;
	}
}
return list;
}

*//**
	 * 是否有顺刻
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean isShun(int lastPai,int[][] memberPai){

int yu = lastPai / 10 - 1;
int mod = lastPai % 10;
if (yu == 3) {
	return false;
}
if (mod == 9) {
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod - 2] >= 1) {
		return true;
	}
} else if (mod == 1) {
	if (memberPai[yu][mod + 1] >= 1 && memberPai[yu][mod + 2] >= 1) {
		return true;
	} 
} else if (mod == 8) {
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod - 2] >= 1) {
		return true;
	} else if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod + 1] >= 1) {
		return true;
	}
} else if (mod == 2) {
	if (memberPai[yu][mod + 1] >= 1 && memberPai[yu][mod + 2] >= 1) {
		return true;
	} else if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod + 1] >= 1) {
		return true;
	}
} else {
	if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod + 1] >= 1) {
		return true;
	} else if (memberPai[yu][mod - 1] >= 1 && memberPai[yu][mod - 2] >= 1) {
		return true;
	} else if (memberPai[yu][mod + 1] >= 1 && memberPai[yu][mod + 2] >= 1) {
		return true;
	}
}
return false;
}

*//**
	 * 牌的类型 一万-九万 11 - 19 一筒-九筒 21 - 29 一索-九索 31-39 将牌转换为二维数组
	 *
	 * 首位是牌型总数
	 * 
	 * @param list
	 * @return
	 */
/*
public static int[][] conversionType(List<Integer> list) {

int[][] allPai = new int[4][10];
for (int i = 0; i < list.size(); i++) {
	Integer pai = list.get(i);
	switch (pai / 10) {
	case 1:
		allPai[0][0] = allPai[0][0] + 1;
		allPai[0][pai % 10] = allPai[0][pai % 10] + 1;
		break;
	case 2:
		allPai[1][0] = allPai[1][0] + 1;
		allPai[1][pai % 10] = allPai[1][pai % 10] + 1;
		break;
	case 3:
		allPai[2][0] = allPai[2][0] + 1;
		allPai[2][pai % 10] = allPai[2][pai % 10] + 1;
		break;
	case 4:
		allPai[3][0] = allPai[3][0] + 1;
		allPai[3][pai % 10] = allPai[3][pai % 10] + 1;
		break;
	default:
		break;
	}
}
return allPai;
}

*//**
	 * 听牌
	 *
	 * @param allPai
	 * @return
	 */
/*
public static List<Integer> tingPai(Map<Integer,Integer> allBasePais,int[][] allPai, List<GPaiQiang.Builder> showPai,int MJType,Map<MajiangRuleType,Integer> rules) {
int pai = 0;
List<Integer> tingTable = new ArrayList<>();
int[][] readyAllPai = allPai;

for(Map.Entry<Integer,Integer> entry:allBasePais.entrySet()){
		pai = entry.getKey();
		int i = pai/10;
		int j = pai%10;
		boolean bool = false;
		if (readyAllPai[i - 1][j] < 4) {// 已经是一杠牌了不处理
			readyAllPai[i - 1][0] = readyAllPai[i - 1][0] + 1;
			readyAllPai[i - 1][j] = readyAllPai[i - 1][j] + 1;
			bool = true;
			if (fitHu(readyAllPai,showPai,MJType,rules)>0) {
				tingTable.add(pai);
			}
			readyAllPai[i - 1][0] = readyAllPai[i - 1][0] - 1;
			readyAllPai[i - 1][j] = readyAllPai[i - 1][j] - 1;
		}// 遍历添加一张 看是否符合胡牌条件
		
	
}
return tingTable;
}

*//**
	 * 李培光：判断有癞子的听牌,通过size
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static List<Integer> tingPaiLai(Map<Integer,Integer> allBasePais,List<Integer> paiList, List<GPaiQiang.Builder> showPai,int laiZi,int MJType,Map<MajiangRuleType,Integer> rules) {
// 复制
List<Integer> tingTable = new ArrayList<>();
List<Integer> shouPai = new ArrayList<Integer>();
shouPai.addAll(paiList);
// list转hashmap（hm是用来判断一条杠）
HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
for (Integer integer : shouPai) {
	if (hm.get(integer) == null) {
		hm.put(integer, 1);
	} else {
		hm.put(hm.get(integer), hm.get(integer) + 1);
	}
}
//int[][] allPai = conversionType(shouPai);

Integer pai = 0;// 添加的牌
for(Map.Entry<Integer,Integer> entry:allBasePais.entrySet()){
	pai = entry.getKey();
	if (hm.get(pai) == null || hm.get(pai) < 5) {
		shouPai.add(pai);
	}else{
		continue;
	}
	if(!hasShun(allPai, pai)){
		continue;
	}
	
	if (huLaizi(allBasePais,shouPai, laiZi,showPai,MJType,rules).size() > 0) {
		tingTable.add(pai);
	}
	shouPai.remove(pai);
}

return tingTable;		
if (tingTable.size() > 0) {
	return true;
} else {
	return false;
}
}

*//**
	 * 花猪,癞子不能当做一种花色
	 * 
	 * @param pai
	 * @return
	 */
/*
public static boolean huaZhu(List<Integer> pai, int laiZi) {
int[][] allPai = conversionType(pai);
int wang = allPai[0][0];// 万的数量
int tong = allPai[1][0];// 筒的数量
int tiao = allPai[2][0];// 条的数量
for (int i = 1; i <= 3; i++) {
	for (int j = 1; j <= 9; j++) {
		int k = Integer.parseInt(i + "" + j);// 数值
		if (k == laiZi && allPai[i - 1][j] > 0)// 癞子
		{
			int num = allPai[i - 1][j];
			switch (i) {
			case 1:
				wang -= num;
				break;
			case 2:
				tong -= num;
				break;
			case 3:
				tiao -= num;
				break;
			default:
				break;
			}
		}
	}
}
if (wang > 0 && tong > 0 && tiao > 0) {
	// 花猪不能胡牌
	return false;
} else {
	return true;
}
}

*//**
	 * 赖子能不能胡
	 *
	 * @param allPai
	 * @return
	 */
/*
public static List<List<Integer>> huLaizi(Map<Integer,Integer> allBasePais,List<Integer> list, int laizi,
	List<GPaiQiang.Builder> showPai, int MJType,Map<MajiangRuleType,Integer> rules) {
List<List<Integer>> huData = new ArrayList<List<Integer>>();
int[][] allPai = conversionType(list);
int num = allPai[laizi / 10 - 1][laizi % 10];
allPai[laizi / 10 - 1][laizi % 10] = 0;
allPai[laizi / 10 - 1][0] = allPai[laizi / 10 - 1][0] - num;

if (num == 0 && fitHu(allPai, showPai, MJType, rules)>0) {
	return huData;
}

boolean fengShun = rules.get(MajiangRuleType.PLAY_FENG_SHUN) == 1;
boolean is13yao = rules.get(MajiangRuleType.RULE_LN_SHISANYAO) == 1;
boolean canDuidui = rules.get(MajiangRuleType.HU_QI_DUI) == 1;
// 当只有一张牌
boolean is258Jiang = rules.get(MajiangRuleType.HU_JIANG) == 1;
if (!is258Jiang && list.size() == 2) {
	for (Integer pai : list) {
		if (!pai.equals(laizi)) {
			List<Integer> datas = new ArrayList<Integer>();
			datas.add(pai);
			huData.add(datas);
			return huData;
		}
	}
}

List<Integer> tingTable = quePai(allPai,fengShun);
if(MJType!=GameType.SDLBMAJIANG){//山东搂宝不加赖子进去
	if(!tingTable.contains(laizi)){
		tingTable.add(laizi);
	}
}
// 全排列
if ((list.size() <= 5 && num >= 2)) {
	tingTable.clear();
	tingTable.addAll(allBasePais.keySet());
}




if (canDuidui) {
	List<Integer> duiting = ZTMahJongRule.quePaiQiDui(allPai, laizi,
			num);
	for (Integer que : duiting) {
		if (!tingTable.contains(que)) {
			tingTable.add(que);
		}
	}
}
//不管有没勾选13幺
if (MJType == GameType.LLAS_MAJIANG
	||MJType == GameType.SDLBMAJIANG) {
	List<Integer> duiting = ZTMahJongRule.quePailn13Yao(allPai, num,
			showPai);
	for (Integer que : duiting) {
		if (!tingTable.contains(que)) {
			tingTable.add(que);
		}
	}
} else if (is13yao || MJType == GameType.SDMAJIANG
		|| MJType == GameType.SDPTMAJIANG
		|| MJType == GameType.SDTAMAJIANG
		|| MJType == GameType.SDJNMAJIANG
		|| MJType == GameType.SDDZMAJIANG
		|| MJType == GameType.SDLYMAJIANG) {
	List<Integer> duiting = ZTMahJongRule.quePai13Yao(allPai, num);
	for (Integer que : duiting) {
		if (!tingTable.contains(que)) {
			tingTable.add(que);
		}
	}
}else if(MJType == GameType.SDLBMAJIANG){
	List<Integer> duiting = ZTMahJongRule.quePai13BuKao(allPai, num);
	for (Integer que : duiting) {
		if (!tingTable.contains(que)) {
			tingTable.add(que);
		}
	}
}
if (tingTable.size() == 0) {
	return huData;
}

// 求3个数的组合个数
List<List<Integer>> out = null;

 用于输出的 
if(num>tingTable.size()){
	out = new ArrayList<>();
	out.add(new ArrayList<Integer>());
	for(int i=0;i<num;i++){
		out.get(0).add(tingTable.get(0));
	}	
	for (List<Integer> line : out) {
		for (Integer pai : line) {
			int yu = pai / 10 - 1;
			int mod = pai % 10;
			allPai[yu][mod] = allPai[yu][mod] + 1;
			allPai[yu][0] = allPai[yu][0] + 1;
		}
		int type = fitHu(allPai, showPai, MJType, rules);
		if (type > 0) {
			if (MJType == GameType.LLAS_MAJIANG) {
				if (type > 1) {
					huData.add(line);
				} else {
					int[][] all = conversionType(list);
					all[laizi / 10 - 1][laizi % 10] = 0;
					all[laizi / 10 - 1][0] = all[laizi / 10 - 1][0] - num;
					if (ZTMahJongRule.isSanMenQi(all, showPai)
							&& ZTMahJongRule.hasYaoJiu(all, showPai)) {
						huData.add(line);
					}
				}
			} else {
				huData.add(line);
			}
		}
		for (Integer pai : line) {
			int yu = pai / 10 - 1;
			int mod = pai % 10;
			allPai[yu][mod] = allPai[yu][mod] - 1;
			allPai[yu][0] = allPai[yu][0] - 1;
		}
	}
}else{
	out = cacheCombine(tingTable.size(), num); 以4取3的排列为例 
	for (List<Integer> line : out) {
		List<Integer> data = new ArrayList<>();
		for (Integer index : line) {
			Integer pai = tingTable.get(index);
			data.add(pai);
			int yu = pai / 10 - 1;
			int mod = pai % 10;
			allPai[yu][mod] = allPai[yu][mod] + 1;
			allPai[yu][0] = allPai[yu][0] + 1;
		}
		int type = fitHu(allPai, showPai, MJType,rules);
		if (type > 0) {
			if (MJType == GameType.LLAS_MAJIANG) {
				if (type > 1) {
					huData.add(data);
				} else {
					int[][] all = conversionType(list);
					all[laizi / 10 - 1][laizi % 10] = 0;
					all[laizi / 10 - 1][0] = all[laizi / 10 - 1][0] - num;
					if (ZTMahJongRule.isSanMenQi(all,
							showPai)&& ZTMahJongRule.hasYaoJiu(all, showPai)) {
						huData.add(data);
					}
				}
			} else {
				huData.add(data);
			}
		}
		for (Integer index : line) {
			Integer pai = tingTable.get(index);
			int yu = pai / 10 - 1;
			int mod = pai % 10;
			allPai[yu][mod] = allPai[yu][mod] - 1;
			allPai[yu][0] = allPai[yu][0] - 1;
		}
	}
}



return huData;
}

*//**
	 * 是否带顺序的牌及其听牌
	 * 
	 * @param allPai
	 * @param pai
	 * @param tingTable
	 * @return
	 */
/*
private static boolean shunFilter(int[][] allPai, int pai,
	List<Integer> tingTable) {
int yu = pai / 10 - 1;
int mod = pai % 10;
// 是否有对子
int size = allPai[yu][mod];
boolean hasShun = false;
// 边张判断

if (mod - 1 > 0 && allPai[yu][mod - 1] >= 1) {
	hasShun = true;
	if (mod + 1 < 10 && !tingTable.contains(pai + 1)) {
		tingTable.add(pai + 1);
	}
	if (mod - 2 > 0 && !tingTable.contains(pai - 2)) {
		tingTable.add(pai - 2);
	}
}
if (mod + 1 < 10 && allPai[yu][mod + 1] >= 1) {
	hasShun = true;
	if (mod + 2 < 10 && !tingTable.contains(pai + 2)) {
		tingTable.add(pai + 2);
	}
	if (mod - 1 > 0 && !tingTable.contains(pai - 1)) {
		tingTable.add(pai - 1);
	}
}
if (mod - 2 > 0 && allPai[yu][mod - 2] >= 1) {
	hasShun = true;
	if (mod - 1 > 0 && !tingTable.contains(pai - 1)) {
		tingTable.add(pai - 1);
	}
	if (size == 1) {
		if (!tingTable.contains(pai)) {
			tingTable.add(pai);
		}
	}
}
if (mod + 2 < 10 && allPai[yu][mod + 2] >= 1) {
	hasShun = true;
	if (mod + 1 < 10 && !tingTable.contains(pai + 1)) {
		tingTable.add(pai + 1);
	}
	if (size == 1) {
		if (!tingTable.contains(pai)) {
			tingTable.add(pai);
		}
	}
}
if (!hasShun && size == 1) {
	if (!tingTable.contains(pai)) {
		tingTable.add(pai);
	}
}
return hasShun;
}

*//**
	 * 是否带顺序的牌及其听牌
	 * 
	 * @param allPai
	 * @param pai
	 * @param tingTable
	 * @return
	 */
/*
private static boolean hasShun(int[][] allPai, int pai) {
int yu = pai / 10 - 1;
int mod = pai % 10;
// 是否有对子
boolean hasShun = false;
// 边张判断
int size = allPai[yu][mod];	
if(yu==3){
	if (size > 0) {
		return true;
	}
}else{
	if (mod - 1 > 0 && allPai[yu][mod - 1] >= 1) {
		return true;
	}
	if (mod + 1 < 10 && allPai[yu][mod + 1] >= 1) {
		return true;
	}
	if (mod - 2 > 0 && allPai[yu][mod - 2] >= 1) {
		return true;
	}
	if (mod + 2 < 10 && allPai[yu][mod + 2] >= 1) {
		return true;
	}
	if (size > 0) {
		return true;
	}
}

return hasShun;
}

*//**
	 * 麻将缺什么牌 型完整
	 * 
	 * @param allPai
	 * @param fengShun 是否算风的顺子
	 * @return
	 */
/*
public static List<Integer> quePai(int[][] allPai,boolean fengShun) {

int pai = 0;
List<Integer> tingTable = new ArrayList<>();
int limit = 3;
if(fengShun){
	limit = 4;
}
for (int i = 1; i <= limit; i++) {
	List<Integer> duizi = new ArrayList<Integer>();
	for (int j = 1; j <= 9; j++) {

		pai = 10 * i + j;
		int yu = pai / 10 - 1;
		int mod = pai % 10;
		// 是否有对子
		int size = allPai[yu][mod];
		if (size == 0) {
			continue;
		}

		boolean hasShun = shunFilter(allPai, pai, tingTable);

		boolean totalShun = isShun(pai, allPai);
		if (size == 1 && !totalShun) {
			if (!tingTable.contains(pai)) {
				tingTable.add(pai);
			}
		}
		
		if (size == 2) {//&& !hasShun
			duizi.add(pai);
		}
		if (size == 2 && hasShun) {
			if (!tingTable.contains(pai)) {
				tingTable.add(pai);
			}
		}
		
		if (size == 2) {
			if (!tingTable.contains(pai)) {
				tingTable.add(pai);
			}
		}
		if (size == 3 && hasShun) {
			duizi.add(pai);
			if (!tingTable.contains(pai)) {
				tingTable.add(pai);
			}
		}
	}
	for (Integer data : duizi) {
		if (!tingTable.contains(data)) {
			tingTable.add(data);
		}
	}
}
// 连续牌缺牌的整体判断
for (int i = 1; i <= limit; i++) {
	Map<Integer, Integer> shun = new HashMap<Integer, Integer>();
	int max = 0;
	int start = 0;
	int yu = i - 1;
	for (int j = 1; j <= 9; j++) {
		int next = 1;
		start = j;
		for (int k = j; k <= 8; k++) {
			if (allPai[yu][k] > 0 && allPai[yu][k + 1] > 0) {
				next++;
			} else {
				break;
			}
		}
		if (next > 3) {
			shun.put(start, next);
		}
		if (next > max) {
			max = next;
			
		}
	}
	for (Map.Entry<Integer, Integer> entry : shun.entrySet()) {

		for (int k = 0; k < entry.getValue(); k++) {
			pai = 10 * i + entry.getKey() + k;
			if (!tingTable.contains(pai)) {
				tingTable.add(pai);
			}
		}
		if (entry.getKey() > 1) {
			pai = 10 * i + entry.getKey() - 1;
			if (!tingTable.contains(pai)) {
				tingTable.add(pai);
			}
		}
		int end = entry.getKey() + entry.getValue();
		if (end < 10) {
			pai = 10 * i + end;
			if (!tingTable.contains(pai)) {
				tingTable.add(pai);
			}
		}

	}
	System.out
			.println(shun.size() + "start:" + start + " -- max" + max);
	shun.clear();

}
//黄冈 风牌的判断TODO
if(!fengShun){
	for (int j = 1; j <= 9; j++) {
		pai = 40 + j;
		if (allPai[3][j] > 0 && allPai[3][j] != 3) {
			tingTable.add(pai);
		}
	}
}



//System.out.println("缺牌" + tingTable);
return tingTable;
}

* 带癞子的七小对的缺牌


public static List<Integer> quePaiQiDui(int[][] allPai , int laizi ,int laizi_num) {

int pai = 0;
int duizi_num = 0;//去除癞子的对
int three_size = 0;

//癞子对，其他对，有一定影响逻辑
//int laizi_num = allPai[laizi / 10 - 1][laizi % 10];//癞子个数
//allPai[laizi / 10 - 1][laizi % 10] = 0;
//allPai[laizi / 10 - 1][0] = allPai[laizi / 10 - 1][0] - laizi_num;
List<Integer> tingTable = new ArrayList<Integer>();
List<Integer> singlePai = new ArrayList<Integer>();//单张牌
List<Integer> threePai = new ArrayList<Integer>();//3张牌
for (int i = 1; i <= 4; i++) {
	for (int j = 1; j <= 9; j++) {
		pai = 10 * i + j;
		int yu = pai / 10 - 1;
		int mod = pai % 10;
		// 是否有对子
		int size = allPai[yu][mod];
		//没有数量或者该牌是癞子
		if (size == 0||pai==laizi) {
			continue;
		}
		if (size == 1) {
			//singlePai.add(pai);
			tingTable.add(pai);
		}
		if (size == 2 ) {
			duizi_num ++;
			tingTable.add(pai);
		}
		if (size == 3) {//龙七对
			duizi_num ++;
			tingTable.add(pai);
			threePai.add(pai);
		}
		if(size==4){
			duizi_num +=2;
		}
	}
}
if (laizi_num == 1 && duizi_num >= 6) {
	tingTable.addAll(singlePai);
	tingTable.addAll(threePai);
}else if (laizi_num == 2 && duizi_num >= 5) {
	tingTable.addAll(singlePai);
	tingTable.addAll(threePai);
}else if (laizi_num == 3 && duizi_num >= 4) {
	tingTable.addAll(singlePai);
	tingTable.addAll(threePai);
}else if (laizi_num == 4 && duizi_num >= 3 ) {
	tingTable.addAll(singlePai);
	tingTable.addAll(threePai);
}
if (threePai.size() == 1 && duizi_num >= 5) {
	tingTable.addAll(threePai);
}
return tingTable;
}

*//**
	 * 麻将缺什么牌 型完整
	 * 
	 * @param allPai
	 * @param fengShun 是否算风的顺子
	 * @return
	 */
/*
public static List<Integer> quePai13Yao(int[][] allPai,int laiNum) {

int pai = 0;
List<Integer> tingTable = new ArrayList<>();
int num = laiNum;
for (int i = 1; i <= 3; i++) {
	if(allPai[i][1]>0){
		num++;
	}
	if(allPai[i][9]>0){
		num++;
	}
}
for (int j = 1; j <= 7; j++) {
	if(allPai[3][j]>0){
		num++;
	}
}
if(num>10){
	for (int i = 1; i <= 3; i++) {
		pai = i*10+1;
		tingTable.add(pai);
		pai = i*10+9;
		tingTable.add(pai);
	}
	for (int i = 1; i <= 7; i++) {
		pai = 40+i;
		tingTable.add(pai);
	}
}
	
//System.out.println("13幺缺牌" + tingTable);
return tingTable;
}

public static List<Integer> quePai13BuKao(int[][] allPai,int laiNum) {
List<Integer> tingTable = new ArrayList<>();
int pai = 0;
for (int i = 0; i < 4; i++) {
	if (i != 3 && allPai[i][0] > 3) {
		return tingTable;
	}
	List<Integer> data = new ArrayList<>();
	for (int j = 1; j <= 9; j++) {
		if (allPai[i][j] > 1) {
			return tingTable;
		} else if (allPai[i][j] == 1) {
			data.add(j);
		}
	}
	if (i != 3) {
		if (data.contains(1) || data.contains(4) || data.contains(7)) {
			if (data.contains(2) || data.contains(5)
					|| data.contains(8) || data.contains(3)
					|| data.contains(6) || data.contains(9)) {
				return tingTable;
			}
		} else if (data.contains(2) || data.contains(5)
				|| data.contains(8)) {
			if (data.contains(1) || data.contains(4)
					|| data.contains(7) || data.contains(3)
					|| data.contains(6) || data.contains(9)) {
				return tingTable;
			}
		} else {
			if (data.contains(2) || data.contains(5)
					|| data.contains(8) || data.contains(1)
					|| data.contains(4) || data.contains(7)) {
				return tingTable;
			}
		}
	}
}
for (int i = 0; i < 4; i++) {
	List<Integer> data = new ArrayList<>();
	for (int j = 1; j <= 9; j++) {
		data.add(j);
	}
	
	if (data.contains(1) || data.contains(4) || data.contains(7)) {
		pai = i*10+1;
		tingTable.add(pai);
		pai = i*10+4;
		tingTable.add(pai);
		pai = i*10+7;
		tingTable.add(pai);
	} else if (data.contains(2) || data.contains(5) || data.contains(8)) {
		pai = i*10+2;
		tingTable.add(pai);
		pai = i*10+5;
		tingTable.add(pai);
		pai = i*10+8;
		tingTable.add(pai);
	} else {
		pai = i*10+3;
		tingTable.add(pai);
		pai = i*10+6;
		tingTable.add(pai);
		pai = i*10+9;
		tingTable.add(pai);
	}
}

for(int i = 41;i <=47;i++){
	tingTable.add(i);
}

return tingTable;
}



*//**
	 * 麻将缺什么牌 型完整
	 * 
	 * @param allPai
	 * @param fengShun 是否算风的顺子
	 * @return
	 */
/*
public static List<Integer> quePailn13Yao(int[][] allPai,int laiNum,List<GPaiQiang.Builder> showPai) {

int pai = 0;
List<Integer> tingTable = new ArrayList<>();
int num = laiNum;
for (GPaiQiang.Builder entry : showPai) {
	if (entry.getType() == OptionsType.SHANG_QIANG
			|| entry.getType() == OptionsType.PENG
			|| entry.getType() == OptionsType.EXPOSED_GANG
			|| entry.getType() == OptionsType.EXTRA_GANG
			|| entry.getType() == OptionsType.DARK_GANG) {
		num += 3;
	}
}

for (int i = 1; i <= 3; i++) {
	if(allPai[i][1]>0){
		num+=allPai[i][1];
	}
	if(allPai[i][9]>0){
		num+=allPai[i][9];
	}
}
for (int j = 1; j <= 7; j++) {
	if(allPai[3][j]>0){
		num+=allPai[3][j];
	}
}
if(num>10){
	for (int i = 1; i <= 3; i++) {
		pai = i*10+1;
		tingTable.add(pai);
		pai = i*10+9;
		tingTable.add(pai);
	}
	for (int i = 1; i <= 7; i++) {
		pai = 40+i;
		tingTable.add(pai);
	}
}			
//System.out.println("13幺缺牌" + tingTable);
return tingTable;
}

public static void main(String[] args) {
long time = System.currentTimeMillis();
List<List<Integer>> test = new ArrayList<>();
List<Integer> t = new ArrayList<>();
test.add(t);
// 求3个数的组合个数
int o1[] = new int[10];  用于输出的 

 用于输出的 
int u1[] = new int[10];  用来标记当前元素所使用的次数 
combine(u1, o1, 10, 4, 0, test);
System.out.println(System.currentTimeMillis() - time);

List<Integer> tingTable = new ArrayList<>();

tingTable.add(1);
tingTable.add(4);
tingTable.add(6);
tingTable.add(7);
tingTable.add(8);
tingTable.add(9);
tingTable.add(11);
tingTable.add(12);
tingTable.add(13);
tingTable.add(14);

// 求3个数的组合个数
time = System.currentTimeMillis();
int o[] = new int[tingTable.size()];  用于输出的 
List<List<Integer>> out = new ArrayList<>();
out.add(new ArrayList<Integer>());
 用于输出的 
int u[] = new int[tingTable.size()];  用来标记当前元素所使用的次数 
permutation(tingTable, u, o, tingTable.size(), 4, 0, out); 以4取3的排列为例 
System.out.println(System.currentTimeMillis() - time);

List<Integer> testList=new ArrayList<>();
testList.add(11);
testList.add(12);
testList.add(13);
testList.add(14);
testList.add(14);
//	List<List<Integer>> a=huLaizi(testList, 17, true);
//	System.out.println(a);
System.out.println("***********");

List<Integer> allPai = new LinkedList<>();
for (int i = 11; i < 40; i++) {
	if (i % 10 == 0) {
		continue;
	}
	for (int j = 0; j < 4; j++) {
		allPai.add(i);
	}
}
// 洗牌 打乱顺序
Collections.shuffle(allPai);
List<Integer> pais = new ArrayList<Integer>();
for (int i = 0; i < 13; i++) {
	Integer remove = allPai.remove(0);
	pais.add(remove);
}
System.out.println((19 / 10 * 10 + 1) + "所有牌" + pais);

for (Integer data : allPai) {
	System.out.println("缺牌" + quePai(conversionType(pais),false));
	Integer result = chosePai(pais, data, 11,false);
	System.out.println("加牌" + data);
	System.out.println("打牌" + result);
	pais.add(data);
	pais.remove(result);
	System.out.println("所有牌" + pais);
	//huLaizi(pais, 11,new ArrayList<GPaiQiang.Builder>(),false);
}
List<Integer> aaa = new ArrayList<Integer>();

int[] array ={23,13,14,24,24,24,25,26,33,33};
for(int data:array){
	aaa.add(data);
}
System.out.println("能胡吗" + aaa);
//System.out.println("能胡吗" + huLaizi(aaa, 23,new ArrayList<GPaiQiang.Builder>(),false));

//		List<Integer> ddd = tingPai(conversionType(aaa));
//		int ccc=tingPai(conversionType(aaa)).size();
//System.out.println(ccc);
}

*//**
	 * 能否飞杠
	 *
	 * @param maJongTable
	 * @param member
	 */
/*
public static boolean canFreeGang(int laizinum, int lastPai,
	List<Integer> memberShouPai) {
int free = laizinum;
if (free == lastPai) {
	return false;
}
int[][] memberPai = ZTMahJongRule.conversionType(memberShouPai);
// 已有牌的数量
int yetNum = memberPai[lastPai / 10 - 1][lastPai % 10];
int freeNum = memberPai[free / 10 - 1][free % 10];
if (freeNum > 0 && yetNum >= 2) {
	return true;
}
return false;
}

*//**
	 * 拥有刻
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean hasKe(int[][] allPai,List<GPaiQiang.Builder>  showPai,int jiang) {
for (GPaiQiang.Builder entry : showPai) {
	if (entry.getType() == OptionsType.SHANG_QIANG
			|| entry.getType() == OptionsType.PENG
			|| entry.getType() == OptionsType.EXPOSED_GANG
			|| entry.getType() == OptionsType.EXTRA_GANG
			|| entry.getType() == OptionsType.DARK_GANG) {
		return true;
	}	
}
// 求ke

for (int i = 0; i < 4; i++) {
	for (int j = 1; j <= 9; j++) {
		if (allPai[i][j] > 0 && allPai[i][j] == 3) {
			int pai = (i + 1) * 10 + j;
			if (pai == jiang) {
				continue;
			}
			return true;
		}
		if (allPai[i][j] > 0 && allPai[i][j] == 4) {
			int pai = (i + 1) * 10 + j;
			if (pai == jiang) {
				continue;
			}
			return true;
		}
	}
}
return false;
}

*//**
	 * 开口
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean isKaiKou(List<GPaiQiang.Builder>  showPai) {
for (GPaiQiang.Builder entry : showPai) {
	if (entry.getType() == OptionsType.CHI
			|| entry.getType() == OptionsType.PENG
			|| entry.getType() == OptionsType.EXPOSED_GANG
			|| entry.getType() == OptionsType.EXTRA_GANG) {
		return true;
	}	
}

return false;
}

public static boolean isFlower(OptionsType optionsType){
boolean flower=false;
List<OptionsType> l=new ArrayList<>();
l.add(OptionsType.EXPOSED_GANG);
l.add(OptionsType.EXTRA_GANG);
l.add(OptionsType.DARK_GANG);
l.add(OptionsType.GUDING_GANG);
l.add(OptionsType.LAIZI_GANG);
l.add(OptionsType.DARK_MING_GANG);//暗杆可摆
if(l.contains(optionsType)){
	flower=true;
}
return flower;
}

*//**
	 * 武汉将一色
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean isJiangYiSe(int[][] allPai, List<GPaiQiang.Builder>  showPai) {
//判断手牌
if (allPai[3][0] > 0) {
	return false;
}
//判断摆牌 40以上为东南西北
for (GPaiQiang.Builder pais : showPai) {
	if (pais.getType() == OptionsType.GUDING_GANG
			|| pais.getType() == OptionsType.LAIZI_GANG
			|| pais.getType() == OptionsType.LIANG_DAO) {
		continue;
	}
	if(pais.getPaiType()>40){
		return false;
	}
}		
for (int i = 0; i < 3; i++) {
	for (int j = 1; j <= 9; j++) {
		if (j%3!=2&&allPai[i][j]>0) {
			return false;
		}
	}
}
for (GPaiQiang.Builder entry : showPai) {
	if (entry.getType() == OptionsType.GUDING_GANG
			|| entry.getType() == OptionsType.LAIZI_GANG) {
		continue;
	}
	for (Integer pai : entry.getPai().getPaiList()) {
		Integer yu = pai / 10 - 1;
		Integer mod = pai % 10;
		if (mod % 3 != 2) {
			return false;
		}
	}
}
return true;
}

*//**
	 * 13 不靠
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean is13BuKao(int[][] allPai,
	List<GPaiQiang.Builder> showPai) {
if (!showPai.isEmpty()) {
	return false;
}

Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < 4; i++) {
	if (i != 3 && allPai[i][0] > 3) {
		return false;
	}
	List<Integer> data = new ArrayList<>();
	for (int j = 1; j <= 9; j++) {
		if (allPai[i][j] > 1) {
			return false;
		} else if (allPai[i][j] == 1) {
			data.add(j);
		}
	}
	if (i != 3) {
		if (data.contains(1) || data.contains(4) || data.contains(7)) {
			if (data.contains(2) || data.contains(5)
					|| data.contains(8) || data.contains(3)
					|| data.contains(6) || data.contains(9)) {
				return false;
			}
			map.put(1, 1);
		} else if (data.contains(2) || data.contains(5)
				|| data.contains(8)) {
			if (data.contains(1) || data.contains(4)
					|| data.contains(7) || data.contains(3)
					|| data.contains(6) || data.contains(9)) {
				return false;
			}
			map.put(2, 2);
		} else {
			if (data.contains(2) || data.contains(5)
					|| data.contains(8) || data.contains(1)
					|| data.contains(4) || data.contains(7)) {
				return false;
			}
			map.put(3, 3);
		}
	}
}
if (map.size() == 3) {
	return true;
}
return false;
}

*//**
	 * 安徽金寨13 不靠
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean isAHJZ13BuKao(int[][] allPai,
	List<GPaiQiang.Builder> showPai) {
if (!showPai.isEmpty()) {
	return false;
}

int count = 0;
for (int i = 0; i < 4; i++) {
	if (i != 3 && allPai[i][0] > 3) {
		return false;
	}
	List<Integer> data = new ArrayList<>();
	for (int j = 1; j <= 9; j++) {
		if (allPai[i][j] > 1) {
			return false;
		} else if (allPai[i][j] == 1) {
			data.add(j);
		}
	}
	if (i != 3) {
		if (data.contains(1) || data.contains(4) || data.contains(7)) {
			if (data.contains(2) || data.contains(5)
					|| data.contains(8) || data.contains(3)
					|| data.contains(6) || data.contains(9)) {
				return false;
			}
			count +=1;
		} else if (data.contains(2) || data.contains(5)
				|| data.contains(8)) {
			if (data.contains(1) || data.contains(4)
					|| data.contains(7) || data.contains(3)
					|| data.contains(6) || data.contains(9)) {
				return false;
			}
			count +=1;
		} else {
			if (data.contains(2) || data.contains(5)
					|| data.contains(8) || data.contains(1)
					|| data.contains(4) || data.contains(7)) {
				return false;
			}
			count +=1;
		}
	}
}
if (count == 3) {
	return true;
}
return false;
}



*//**
	 * 13 幺
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean is13Yao(int[][] allPai,
	List<GPaiQiang.Builder> showPai) {
if (!showPai.isEmpty()) {
	return false;
}
// 只能19
for (int i = 0; i < 3; i++) {
	if (allPai[i][0] > (allPai[i][1] + allPai[i][9])) {
		return false;
	}
	if (allPai[i][1] == 0 || allPai[i][9] == 0) {
		return false;
	}
}
//风
for (int j = 1; j <= 7; j++) {
	if (allPai[3][j] < 1) {
		return false;
	} 
}
return true;
}

*//**
	 * 辽宁13 幺 不用全齐只要是幺
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean isln13Yao(int[][] allPai,
	List<GPaiQiang.Builder> showPai) {
for (GPaiQiang.Builder entry : showPai) {
	if (entry.getType() == OptionsType.PENG
			|| entry.getType() == OptionsType.SHANG_QIANG
			|| entry.getType() == OptionsType.DARK_GANG
			|| entry.getType() == OptionsType.EXPOSED_GANG
			|| entry.getType() == OptionsType.EXTRA_GANG) {
		int pai = entry.getPaiType();
		int mod = pai % 10;
		int yu = pai / 10;
		if (yu == 4) {
			continue;
		} else if (mod == 1 || mod == 9) {
			continue;
		}
		return false;
	} else {
		return false;
	}
}

// 只能19
for (int i = 0; i < 3; i++) {
	for (int j = 2; j < 9; j++) {
		if (allPai[i][j] > 0) {
			return false;
		}
	}

}
return true;
}


*//**
	 * 单三
	 * 
	 * @param allPai
	 * @return
	 */
/*
*//**
	 * @param allPai
	 * @param destPai
	 * @return
	 */
/*
public static boolean isDanSan(int[][] allPai,Integer destPai) {
if (destPai != 23) {
	return false;
}
if (allPai[1][1] == 0 || allPai[1][2] == 0) {
	return false;
}
allPai[1][1] = allPai[1][1] - 1;
allPai[1][2] = allPai[1][2] - 1;
allPai[1][3] = allPai[1][3] - 1;
allPai[1][0] = allPai[1][0] - 3;

if(huPai(allPai, false)){
	allPai[1][1] = allPai[1][1] + 1;
	allPai[1][2] = allPai[1][2] + 1;
	allPai[1][3] = allPai[1][3] + 1;
	allPai[1][0] = allPai[1][0] + 3;
	return true;
}		
allPai[1][1] = allPai[1][1] + 1;
allPai[1][2] = allPai[1][2] + 1;
allPai[1][3] = allPai[1][3] + 1;
allPai[1][0] = allPai[1][0] + 3;
return false;
}

*//**
	 * 夹五
	 * 
	 * @param allPai
	 * @return
	 */
/*
*//**
	 * @param allPai
	 * @param destPai
	 * @return
	 */
/*
public static boolean isJiaWu(int[][] allPai,Integer destPai) {
if (destPai != 15) {
	return false;
}
if (allPai[0][4] == 0 || allPai[0][6] == 0) {
	return false;
}
allPai[0][4] = allPai[0][4] - 1;
allPai[0][5] = allPai[0][5] - 1;
allPai[0][6] = allPai[0][6] - 1;
allPai[0][0] = allPai[0][0] - 3;

if(huPai(allPai, false)){
	allPai[0][4] = allPai[0][4] + 1;
	allPai[0][5] = allPai[0][5] + 1;
	allPai[0][6] = allPai[0][6] + 1;
	allPai[0][0] = allPai[0][0] + 3;
	return true;
}		
allPai[0][4] = allPai[0][4] + 1;
allPai[0][5] = allPai[0][5] + 1;
allPai[0][6] = allPai[0][6] + 1;
allPai[0][0] = allPai[0][0] + 3;
return false;
}

*//**
	 * 吊七
	 * 
	 * @param allPai
	 * @return
	 */
/*
*//**
	 * @param allPai
	 * @param destPai
	 * @return
	 */
/*
public static boolean isDiaoQi(int[][] allPai, Integer destPai,boolean isDui) {
if (destPai != 37) {
	return false;
}
if (isDui) {
	if (allPai[2][7] == 2 || allPai[2][7] == 4) {
		return true;
	}
} else {
	if (getJiangPai(allPai) == 37) {
		return true;
	}
}

return false;
}

*//**
	 * 风一色
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean isFengYiSe(int[][] allPai, List<GPaiQiang.Builder>  showPai) {
int index = 0;
int last = 0;
for (GPaiQiang.Builder pais : showPai) {
	if (pais.getType() == OptionsType.GUDING_GANG
			|| pais.getType() == OptionsType.LAIZI_GANG) {
		continue;
	}
	if (pais.getPaiType() / 10!=4) {
		return false;
	}
	index++;
}
if (allPai[3][0] == 0) {
	return false;
} else if (allPai[0][0] == 0 && allPai[1][0] == 0 && allPai[2][0] == 0) {
	// 风一色 去掉中发白
	if (allPai[3][5] == 0 && allPai[3][6] == 0 && allPai[3][7] == 0){
		return true;
	}	
	return true;
}
return false;
}

public static boolean isMenQianQing(List<GPaiQiang.Builder> showPai){
boolean flag=true;
for (GPaiQiang.Builder builder : showPai) {
	if (builder.getType() == OptionsType.CHI || builder.getType() == OptionsType.PENG
			|| builder.getType() == OptionsType.DARK_GANG || builder.getType() == OptionsType.EXPOSED_GANG
			|| builder.getType() == OptionsType.EXTRA_GANG || builder.getType() == OptionsType.CHI) {
		flag = false;
		break;
	}
}
return flag;
}

public static boolean isSDMenQianQing(List<GPaiQiang.Builder> showPai) {
boolean flag = true;
for (GPaiQiang.Builder builder : showPai) {
	if (builder.getType() != OptionsType.DARK_GANG) {
		flag = false;
		break;
	}
}
return flag;
}

*//**
	 * 刻或者有字做将
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean keOrZiJiang(int[][] allPai,List<GPaiQiang.Builder> showPai) {
// 刻或者有字做将
int jiang = getJiangPai(allPai);
Integer yu = jiang / 10 - 1;
Integer mod = jiang % 10;
if ((yu == 3&&mod>4) || hasKe(allPai,showPai,jiang)) {
	return true;
}
return false;

}

*//**
	 * 单纯碰碰胡
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean isPPH(int[][] allPai,List<GPaiQiang.Builder> paiQiang) {
// 是否碰碰胡
// 求碰碰胡的将
int jiang = getJiangPai(allPai);
for (int i = 0; i < 4; i++) {
	for (int j = 1; j <= 9; j++) {
		if (allPai[i][j] > 0 && allPai[i][j] == 5||allPai[i][j] == 2) {
			jiang = (i+1)*10+j;
		}
	}
}
Integer yu = jiang / 10 - 1;
Integer mod = jiang % 10;
//碰碰胡不能吃，吃了就不是碰碰胡了
for (GPaiQiang.Builder pais : paiQiang) {
	if (pais.getType() == OptionsType.CHI) {
		return false;
	}
}
// 求单
for (int i = 0; i < 4; i++) {
	for (int j = 1; j <= 9; j++) {
		if (i == yu && j == mod) {
			continue;
		}
		if (allPai[i][j] > 0 && allPai[i][j] != 3) {
			return false;
		}
	}
}

return true;


}

*//**
	 * 258作将 清一色不用
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean is258Jiang(int[][] allPai,int jiang) {
Integer yu = jiang / 10 - 1;
Integer mod = jiang % 10;
// 是否碰碰胡
boolean penpen = true;
for (int i = 0; i < 4; i++) {
	for (int j = 1; j <= 9; j++) {
		if (i == yu && j == mod) {
			continue;
		}
		if (allPai[i][j] > 0 && allPai[i][j] != 3) {
			penpen = false;
			break;
		}
	}
	if (!penpen) {
		break;
	}
}
//碰碰胡任意将
if(penpen){
	return true;
}
// 是否清一色
boolean flag = false;
if (allPai[3][0] == 0) {
	if (allPai[0][0] == 0 && allPai[1][0] == 0) {
		flag = true;
	}else if (allPai[0][0] == 0 && allPai[2][0] == 0) {
		flag = true;
	}else if (allPai[1][0] == 0 && allPai[2][0] == 0) {
		flag = true;
	}
} else if (allPai[0][0] == 0 && allPai[1][0] == 0 && allPai[2][0] == 0) {
	// 风一色 去掉中发白
	if (allPai[3][5] == 0 && allPai[3][6] == 0 && allPai[3][7] == 0){
		flag = true;
	}	
}
if(flag){
	return true;
}
//
if (allPai[3][0] > 0) {
	for (int j = 1; j <= 9; j++) {
		if (allPai[3][j]>0&&allPai[3][j] !=3) {
			return false;
		}
	}
}

//不是258将
if(mod%3!=2){
	return false;
}
return true;
}

*//**
	 * 是否三门齐
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean isSanMenQi(int[][] allPai,
	List<GPaiQiang.Builder> paiQiang) {
List<Integer> huaSe = new ArrayList<Integer>();
if (allPai[0][0] > 0) {
	huaSe.add(0);
}
if (allPai[1][0] > 0) {
	huaSe.add(1);
}
if (allPai[2][0] > 0) {
	huaSe.add(2);
}


for (GPaiQiang.Builder data:paiQiang) {		
	int type = data.getPaiType() / 10 - 1;
	if (type!=3&&!huaSe.contains(type)) {
		huaSe.add(type);
	}
}

return huaSe.size() > 2;
}

*//**
	 * 缺的类型下标
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean isQueYiMen(int[][] allPai,
	List<GPaiQiang.Builder> paiQiang, int tuopai) {
List<Integer> huaSe = new ArrayList<Integer>();
Integer yu = tuopai / 10 - 1;
Integer mod = tuopai % 10;
int num = allPai[yu][mod];
if (allPai[0][0] > 0) {
	huaSe.add(0);
}
if (allPai[1][0] > 0) {
	huaSe.add(1);
}
if (allPai[2][0] > 0) {
	huaSe.add(2);
}
if (num == allPai[yu][0]) {
	huaSe.remove(yu);
}

for (GPaiQiang.Builder data:paiQiang) {		
	yu = data.getPaiType() / 10 - 1;
	if (!huaSe.contains(yu)) {
		huaSe.add(yu);
	}
}

return huaSe.size() > 2 ? false : true;
}

*//**
	 * 至少带1张幺九
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean hasYaoJiu(int[][] allPai,
	List<GPaiQiang.Builder> paiQiang) {

for (GPaiQiang.Builder data:paiQiang) {		
	if(data.getType()==OptionsType.CHI){
		for(int pai:data.getPai().getPaiList()){
			if (pai/10==4||(pai%10 ==1||pai%10 ==9)) {
				return true;
			}
		}
	}else{
		int pai = data.getPaiType();
		if (pai/10==4||(pai%10 ==1||pai%10 ==9)) {
			return true;
		}
	}
}
if (allPai[0][1] > 0||allPai[0][9] > 0) {
	return true;
}
if (allPai[1][1] > 0||allPai[1][9] > 0) {
	return true;
}
if (allPai[2][1] > 0||allPai[2][9] > 0) {
	return true;
}
if (allPai[3][0] > 0) {
	return true;
}
return false;
}

*//**
	 * 缺的类型下标
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static int choseQueType(int[][] allPai, int tuopai) {
if (allPai[0][0] == 0) {
	return 0;
}
if (allPai[1][0] == 0) {
	return 1;
}
if (allPai[2][0] == 0) {
	return 2;
}
int yu = tuopai / 10 - 1;
int mod = tuopai % 10;
int num = allPai[yu][mod];
int index = 0;
for (int count = 0; count < 2; count++) {
	int last = allPai[index][0];
	int next = allPai[count + 1][0];
	// 除去坨牌的数量
	if (index == yu) {
		last = last - num;
	}
	if (next == yu) {
		next = next - num;
	}
	if (next < last) {
		index = count + 1;
	}
}
return index;
}

*//**
	 * 选择牌
	 *
	 * @param allPai
	 * @return
	 */
/*
public static int chosePai(List<Integer> list, int start, int tuopai,boolean isQue) {
int[][] allPai = conversionType(list);
// 只留一种花色
if (isQue&&allPai[0][0] > 0 && allPai[1][0] > 0 && allPai[2][0] > 0) {
	int yu = tuopai / 10 - 1;
	int mod = tuopai % 10;
	int num = allPai[yu][mod];

	int index = choseQueType(allPai, tuopai);

	if (yu == index && allPai[index][0] == num) {

	} else {
		// 有杂牌先打
		for (int j = 1; j <= 9; j++) {
			int pai = 10 * (index + 1) + j;
			if (allPai[index][j] > 0 && pai != tuopai) {
				return pai;
			}
		}
	}

}
int result = start;
int priority = -1;
for (int i = 1; i <= 3; i++) {
	for (int j = 1; j <= 9; j++) {
		int pai = 10 * i + j;
		int yu = pai / 10 - 1;
		int mod = pai % 10;

		// 是否有对子
		int size = allPai[yu][mod];
		if (size == 0) {
			continue;
		}
		int curPriority = 1;
		// 是否有顺序
		boolean hasShun = false;
		if (mod == 9) {
			if (allPai[yu][mod - 1] >= 1 && allPai[yu][mod - 2] >= 1) {
				curPriority = 4;
				hasShun = true;
			} else if (allPai[yu][mod - 1] >= 1
					|| allPai[yu][mod - 2] >= 1) {
				curPriority = 1;
			} else {
				curPriority = 0;
			}
		} else if (mod == 1) {
			if (allPai[yu][mod + 1] >= 1 && allPai[yu][mod + 2] >= 1) {
				curPriority = 4;
				hasShun = true;
			} else if (allPai[yu][mod + 1] >= 1
					|| allPai[yu][mod + 2] >= 1) {
				curPriority = 1;
			} else {
				curPriority = 0;
			}
		} else if (mod == 8) {
			if (allPai[yu][mod - 1] >= 1 && allPai[yu][mod - 2] >= 1) {
				curPriority = 4;
				hasShun = true;
			} else if (allPai[yu][mod - 1] >= 1
					&& allPai[yu][mod + 1] >= 1) {
				curPriority = 4;
				hasShun = true;
			} else if (allPai[yu][mod - 1] >= 1) {
				curPriority = 3;
			} else if (allPai[yu][mod - 2] >= 1
					|| allPai[yu][mod + 1] >= 1) {
				curPriority = 1;
			} else {
				curPriority = 0;
			}
		} else if (mod == 2) {
			if (allPai[yu][mod + 1] >= 1 && allPai[yu][mod + 2] >= 1) {
				curPriority = 4;
				hasShun = true;
			} else if (allPai[yu][mod - 1] >= 1
					&& allPai[yu][mod + 1] >= 1) {
				curPriority = 4;
				hasShun = true;
			} else if (allPai[yu][mod + 1] >= 1) {
				curPriority = 3;
			} else if (allPai[yu][mod + 2] >= 1
					|| allPai[yu][mod - 1] >= 1) {
				curPriority = 1;
			} else {
				curPriority = 0;
			}
		} else {
			if (allPai[yu][mod - 1] >= 1 && allPai[yu][mod + 1] >= 1) {
				curPriority = 4;
				hasShun = true;
			} else if (allPai[yu][mod - 1] >= 1
					&& allPai[yu][mod - 2] >= 1) {
				curPriority = 4;
				hasShun = true;
			} else if (allPai[yu][mod + 1] >= 1
					&& allPai[yu][mod + 2] >= 1) {
				curPriority = 4;
				hasShun = true;
			} else if (allPai[yu][mod - 1] >= 1
					|| allPai[yu][mod + 1] >= 1) {
				curPriority = 3;
			} else if (allPai[yu][mod - 2] >= 1
					|| allPai[yu][mod + 2] >= 1) {
				curPriority = 1;
			} else {
				curPriority = 0;
			}
		}
		if (size > 2 && hasShun) {
			curPriority = 3;
		}
		if (size > 2) {
			curPriority = 4;
		} else if (size > 1 && hasShun) {
			curPriority = 1;
		} else if (size > 1) {
			curPriority = curPriority > 1 ? curPriority : 2;
		} else if (size > 0 && hasShun) {

		} else {
			// 啥都不是
			// curPriority = curPriority>1?curPriority:0;
		}
		if (pai == tuopai||pai == 45) {
			curPriority = 5;
		}

		if (priority == -1) {
			priority = curPriority;
			result = pai;
			continue;
		}

		if (curPriority >= priority) { // 这个牌有价值
			
			 * if (result == pai && priority == 1) { priority =
			 * curPriority; }
			 
			continue;
		} else {
			priority = curPriority;
			result = pai;
		}
	}
}
return result;

}

*//**
	 * 胡牌 龙七对可以看做巧七对的一种
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean duiduiHu(int[][] allPai) {
// 对对胡
boolean isDuizi = true;
for (int i = 0; i <= 3; i++) {
	for (int j = 1; j <= 9; j++) {
		if (allPai[i][j] > 0 && (allPai[i][j] != 2&&allPai[i][j] != 4)) {//4为龙7对的判定
			isDuizi = false;
			break;
		}
	}
	if (!isDuizi) {
		break;
	}
}
if (isDuizi) {
	return true;
}
return false;
}

*//**
	 * 连6判断
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean lian6(int[][] allPai) {
for (int i = 0; i <= 3; i++) {
	int num = 0;
	for (int j = 1; j <= 9; j++) {
		if (allPai[i][j] > 0) {//4为龙7对的判定
			num++;
			if(num >= 6) {
				return true;
			}
		} else {
			num=0;
		}
	}
	if(num >= 6) {
		return true;
	}
}
return false;
}


*//**
	 * 连9判断
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static boolean lian9(int[][] allPai) {
for (int i = 0; i <= 3; i++) {
	int num = 0;
	for (int j = 1; j <= 9; j++) {
		if (allPai[i][j] > 0) {//4为龙7对的判定
			num++;
			if(num >= 9) {
				return true;
			}
		} else {
			num=0;
		}
	}
	if(num >= 9) {
		return true;
	}
}
return false;
}

*//**
	 * 判断7对的四牌数
	 * 
	 * @param allPai
	 * @return
	 */
/*
public static int getQiduiHuType(int[][] allPai) {
int result = 0;
for (int i = 0; i <= 3; i++) {
	for (int j = 1; j <= 9; j++) {
		if (allPai[i][j] > 0 && allPai[i][j] == 4) {// 4为龙7对的判定
			result++;
		}
	}
}
return result;
}

//判断7对的方法
public static boolean otherQiDui(int[][] shouPaiArr,int laiZiNum,int laiZi){
int paiNum=14;
paiNum = paiNum - laiZiNum;
int tempNum = 0;
for (int i = 0; i <= 3; i++) {
	for (int j = 1; j <= 9; j++) {
		if ((i+1) * 10 + j == laiZi) {
			continue;
		} else {
			tempNum = shouPaiArr[i][j];
			if (tempNum == 0) {
				continue;
			}
			if (tempNum == 2) {
				paiNum = paiNum - 2;
			}
			if (tempNum == 3) {
				paiNum = paiNum - 2;
			}
			if (tempNum == 4) {
				paiNum = paiNum - 4;
			}
		}
	}
}
if (laiZiNum >= paiNum){
	return true;
}else{
	return false;
}
}

// 有癞子的豪7对的判断 癞子23
// 牌型1  23 23 23 11 11 11 12 12 13 13 33 33 35 36 不为豪7对 
// 牌型2  23 23 23 23 11 11 11 11 23 24 25 27 33 34 豪7对 
public static int HavefourSameNum(int[][] allpai,int laiZi) {
int result = 0;
for (int i = 0; i < 4; i++) {
	for (int j = 1; j < 10; j++) {
		int k=(i+1)*10+j;
		if (allpai[i][j] == 4&&k!=laiZi) {
			result++;
		}
	}
}
return result;
}

*//**
	 * 将牌
	 *
	 * @param allPai
	 * @return
	 */
/*
public static int getJiangPai(int[][] allPai) {

int jiangPos = 0;// 哪一组牌中存在将
int yuShu = 0;
boolean jiangExisted = false;
for (int i = 0; i < 4; i++) {
	yuShu = allPai[i][0] % 3;
	if (yuShu == 1) {
		return 0;
	}
	if (yuShu == 2) {
		if (jiangExisted) {
			return 0;
		}
		jiangPos = i;
		jiangExisted = true;
	}
}

boolean success = false;
for (int j = 1; j <= 9; j++) {
	if (allPai[jiangPos][j] >= 2) {
		allPai[jiangPos][j] = allPai[jiangPos][j] - 2;
		allPai[jiangPos][0] = allPai[jiangPos][0] - 2;

		int[] temp = new int[10];
		for (int k = 0; k <= 9; k++) {
			temp[k] = allPai[jiangPos][k];
		}
		if (analyze(temp, jiangPos == 3)) {
			success = true;
		}
		allPai[jiangPos][j] = allPai[jiangPos][j] + 2;
		allPai[jiangPos][0] = allPai[jiangPos][0] + 2;
		if (success) {
			return (jiangPos+1)*10+j;
		}
	}
}
return 0;
}



*//**
	 * 正常胡手牌
	 *
	 * @param allPai
	 * @param ziShun 是否字牌有顺子
	 * @return
	 */
/*
public static boolean huPai(int[][] allPai,boolean need258) {

int jiangPos = 0;// 哪一组牌中存在将
int yuShu = 0;
boolean jiangExisted = false;
for (int i = 0; i < 4; i++) {
	yuShu = allPai[i][0] % 3;
	if (yuShu == 1) {
		return false;
	}
	if (yuShu == 2) {
		if (jiangExisted) {
			return false;
		}
		jiangPos = i;
		jiangExisted = true;
	}
}

for (int i = 0; i < 4; i++) {
	if (i != jiangPos) {
		int[] temp = new int[10];
		for (int j = 0; j <= 9; j++) {
			temp[j] = allPai[i][j];
		}
		
		 * if (0 != analyze(temp).length) { return false; }
		 
		if (!analyze(temp, i == 3)) {
			return false;
		}
	}
}

boolean success = false;
for (int j = 1; j <= 9; j++) {
	if (allPai[jiangPos][j] >= 2) {
		allPai[jiangPos][j] = allPai[jiangPos][j] - 2;
		allPai[jiangPos][0] = allPai[jiangPos][0] - 2;

		int[] temp = new int[10];
		for (int k = 0; k <= 9; k++) {
			temp[k] = allPai[jiangPos][k];
		}
		if (analyze(temp, jiangPos == 3)) {
			success = true;
		}
		allPai[jiangPos][j] = allPai[jiangPos][j] + 2;
		allPai[jiangPos][0] = allPai[jiangPos][0] + 2;
		if (success) {
			if (need258 && j % 3 != 2) {
				return false;
			}
			if (need258 && jiangPos==3) {
				return false;
			}
			break;
		}
	}
}
return success;
}

*//**
	 * 正常胡手牌
	 *
	 * @param allPai
	 * @param ziShun 是否字牌有顺子
	 * @return
	 */
/*
public static boolean huPaiShun(int[][] allPai,boolean need258) {

int jiangPos = 0;// 哪一组牌中存在将
int yuShu = 0;
boolean jiangExisted = false;
for (int i = 0; i < 4; i++) {
	yuShu = allPai[i][0] % 3;
	if (yuShu == 1) {
		return false;
	}
	if (yuShu == 2) {
		if (jiangExisted) {
			return false;
		}
		jiangPos = i;
		jiangExisted = true;
	}
}

for (int i = 0; i < 4; i++) {
	if (i != jiangPos) {
		int[] temp = new int[10];
		for (int j = 0; j <= 9; j++) {
			temp[j] = allPai[i][j];
		}
		
		 * if (0 != analyze(temp).length) { return false; }
		 
		if (!analyzeShun(temp, i == 3)) {
			return false;
		}
	}
}

boolean success = false;
for (int j = 1; j <= 9; j++) {
	if (allPai[jiangPos][j] >= 2) {
		allPai[jiangPos][j] = allPai[jiangPos][j] - 2;
		allPai[jiangPos][0] = allPai[jiangPos][0] - 2;

		int[] temp = new int[10];
		for (int k = 0; k <= 9; k++) {
			temp[k] = allPai[jiangPos][k];
		}
		if (analyzeShun(temp, jiangPos == 3)) {
			success = true;
		}
		allPai[jiangPos][j] = allPai[jiangPos][j] + 2;
		allPai[jiangPos][0] = allPai[jiangPos][0] + 2;
		if (success) {
			if (need258 && j % 3 != 2) {
				return false;
			}
			if (need258 && jiangPos==3) {
				return false;
			}
			break;
		}
	}
}
return success;
}

*//**
	 * 胡 -> 七小对 将一色 正常顺刻胡
	 * 
	 * @param table
	 * @param role
	 * @return
	 */
/*
public static int fitHu(int[][] memberPai, List<GPaiQiang.Builder>  showPai,int MJType,Map<MajiangRuleType,Integer> rules){
boolean is258Jiang = rules.get(MajiangRuleType.HU_JIANG) == 1;
boolean canDuidui = rules.get(MajiangRuleType.HU_QI_DUI) == 1;


boolean is258 = true;
boolean isFengShun = rules.get(MajiangRuleType.PLAY_FENG_SHUN) == 1;
if( MJType == GameType.JZMAJIANG) {
	is258 = false;
	if (ZTMahJongRule.otherQiDui(memberPai, 0, 0)) {
		return FitHuType.QIDUI;
	}
	if (ZTMahJongRule.huPai(memberPai, is258)) {
		return FitHuType.PINGHU;
	}
	return FitHuType.NO;
}

if (MJType == GameType.MAJIANG|| MJType == GameType.SDMAJIANG|| MJType == GameType.SDPTMAJIANG|| MJType == GameType.SDJNMAJIANG
		|| MJType == GameType.SDTAMAJIANG||MJType == GameType.SDDZMAJIANG|| MJType == GameType.SDLYMAJIANG
		||MJType == GameType.HAMAJIANG||MJType == GameType.LTMAJIANG||MJType == GameType.MCMAJIANG||MJType == GameType.QCMAJIANG
		||MJType == GameType.WXMAJIANG||MJType == GameType.XSMAJIANG||MJType == GameType.YSMAJIANG||MJType == GameType.DYMAJIANG
		||MJType == GameType.DNMAJIANG||MJType == GameType.SDTDHMAJIANG||MJType == GameType.HSHHMAJIANG||MJType == GameType.SDLBMAJIANG
  		|| MJType == GameType.SDWFMAJIANG|| MJType == GameType.XSDLYMAJIANG) {
	if(MJType == GameType.SDTDHMAJIANG|| MJType == GameType.SDWFMAJIANG|| MJType == GameType.XSDLYMAJIANG||MJType == GameType.SDLBMAJIANG){
		if(is258Jiang){
			// 将一色,山东推倒胡只有勾选258将之后才能胡
			if (ZTMahJongRule.isJiangYiSe(memberPai, showPai)) {
				return FitHuType.JIANGYISE;
			}
		}
	}else{
		// 将一色
		if (ZTMahJongRule.isJiangYiSe(memberPai, showPai)) {
			return FitHuType.JIANGYISE;
		}
	}
	if (MJType == GameType.SDMAJIANG || MJType == GameType.SDPTMAJIANG|| MJType == GameType.SDTAMAJIANG|| MJType == GameType.SDJNMAJIANG||MJType == GameType.SDDZMAJIANG|| MJType == GameType.SDLYMAJIANG) {
		if (isFengYiSe(memberPai, showPai)) {
			Integer hyu = 45 / 10 - 1;
			Integer hmod = 45 % 10;
			if (memberPai[hyu][hmod] == 0) {
				return FitHuType.FENGYISE;
			}
		}
	} else {
		if (isFengYiSe(memberPai, showPai)) {
			return FitHuType.FENGYISE;
		}
	}
	
	// 清一色是特殊
	if (isQingYiSe(memberPai, showPai)||judgePPH(memberPai, 49, showPai)) {
		is258 = false;
	}
}
if (MJType == GameType.SDMAJIANG || MJType == GameType.SDPTMAJIANG|| MJType == GameType.SDJNMAJIANG
		|| MJType == GameType.SDTAMAJIANG||MJType == GameType.SDDZMAJIANG|| MJType == GameType.SDLYMAJIANG
		|| MJType == GameType.SDTDHMAJIANG||MJType == GameType.SDLBMAJIANG|| MJType == GameType.SDWFMAJIANG|| MJType == GameType.XSDLYMAJIANG) {
	if (is13BuKao(memberPai, showPai)) {
		return FitHuType.SHISANBUKAO;
	}
	if (is13Yao(memberPai, showPai)) {
		return FitHuType.SHISANYAO;
	}
}

if (MJType == GameType.AHJZMAJIANG) {
	if (isAHJZ13BuKao(memberPai, showPai)) {
		return FitHuType.SHISANBUKAO;
	}
}

if(MJType==GameType.JDZMAJIANG){
	if(judgeShiSanLan(memberPai, showPai)){
		return FitHuType.SHISANLAN;
	}
	if(is13Yao(memberPai, showPai)){
		return FitHuType.SHISANYAO;
	}
}

boolean is13yao = rules.get(
		MajiangRuleType.RULE_LN_SHISANYAO) == 1;
// 有没13幺都算
if (MJType == GameType.LLAS_MAJIANG) {
	if (isln13Yao(memberPai, showPai)) {
		return FitHuType.LN13YAO;
	}
}
if(is13yao){
	
	if (is13Yao(memberPai, showPai)) {
		return FitHuType.SHISANYAO;
	}
}


//如果是两张牌 要提前判断是否将
if(!ZTMahJongRule.isErRight(memberPai, showPai)){
	return false;
}
//七小对（注意还有红中癞子杠比较特别）
//七小对（注意还有红中癞子杠比较特别）
if (MJType == GameType.MAJIANG || MJType == GameType.HAMAJIANG || MJType == GameType.LTMAJIANG
		|| MJType == GameType.MCMAJIANG || MJType == GameType.QCMAJIANG || MJType == GameType.WXMAJIANG
		|| MJType == GameType.XSMAJIANG || MJType == GameType.YSMAJIANG|| MJType == GameType.DYMAJIANG
		|| MJType == GameType.CZMAJIANG || MJType == GameType.SDTDHMAJIANG || MJType == GameType.AHJZMAJIANG
		|| MJType == GameType.DNMAJIANG || MJType == GameType.HSHHMAJIANG || MJType == GameType.SDWFMAJIANG
		|| MJType == GameType.XSDLYMAJIANG ||MJType == GameType.SDLBMAJIANG) {
	boolean flag = true;
	for (Builder builder : showPai) {
		if (builder.getType() == OptionsType.CHI || builder.getType() == OptionsType.DARK_GANG
				|| builder.getType() == OptionsType.EXPOSED_GANG || builder.getType() == OptionsType.EXTRA_GANG
				|| builder.getType() == OptionsType.PENG) {
			flag = false;
			break;
		}
	}
	if (flag == true) {
		if (ZTMahJongRule.duiduiHu(memberPai)) {
			return FitHuType.QIDUI;
		}
	}
} else {
	if (canDuidui && showPai.isEmpty()) {
		if (ZTMahJongRule.duiduiHu(memberPai)) {
			return FitHuType.QIDUI;
		}
	}
}

if (MJType == GameType.HHMAJIANG || MJType == GameType.JYMAJIANG|| MJType == GameType.JSPX|| MJType == GameType.JSFX||MJType == GameType.GDMAJIANG||MJType == GameType.CZMAJIANG
		||MJType == GameType.LLAS_MAJIANG||MJType == GameType.DNMAJIANG
		||MJType == GameType.JDZMAJIANG ||MJType == GameType.AHJZMAJIANG ||MJType == GameType.HSHHMAJIANG) {

	is258 = false;
}

if (MJType == GameType.SDMAJIANG|| MJType == GameType.SDPTMAJIANG
		|| MJType == GameType.SDJNMAJIANG|| MJType == GameType.SDTAMAJIANG
		||MJType == GameType.SDDZMAJIANG|| MJType == GameType.SDLYMAJIANG
		|| MJType == GameType.LLAS_MAJIANG || MJType == GameType.SDTDHMAJIANG
		|| MJType == GameType.SDLBMAJIANG ||MJType == GameType.SDWFMAJIANG
		|| MJType == GameType.XSDLYMAJIANG ) {
	if (!is258Jiang) {
		is258 = false;
	}
}



boolean huPai = ZTMahJongRule.huPai(memberPai, is258);
if (MJType == GameType.LLAS_MAJIANG && huPai) {
	*//**
		 * 1，必须带一副刻字（杠也算）或者中发白为将牌时无需刻子 2，桶万条三门必须齐 3，至少带1张幺九（一九万条筒、东南西北、中发白）
		 * 4，七对，十三幺，清一色不用满足上述条件 5，飘（即碰碰胡）只要满足条件1即可 6，将牌可以为任意一对
		 */
/*
if (isQingYiSe(memberPai, showPai)||isHunYiSe(memberPai, showPai)) {
return FitHuType.LN_QINGYISE;
} else if (isPPH(memberPai, showPai)) {

* if(keOrZiJiang(memberPai, showPai)){
* 
* }

return FitHuType.PPH;
} else {
if (isSanMenQi(memberPai, showPai)
	&& keOrZiJiang(memberPai, showPai)
	&& hasYaoJiu(memberPai, showPai)) {
return FitHuType.PINGHU;
}
}
return FitHuType.NO;
}
if(huPai){
return FitHuType.PINGHU;
}else{
return FitHuType.NO;
}
}

public static boolean isQingYiSe(int[][] memberPai, List<Builder> showPai) {
int index = 0;
int last = 0; 
for (GPaiQiang.Builder pais : showPai) {
if (pais.getType() == OptionsType.GUDING_GANG
|| pais.getType() == OptionsType.LAIZI_GANG
|| pais.getType() == OptionsType.LIANG_DAO) {
continue;
}
if (index == 0) {
last = pais.getPaiType() / 10;
} else {
if (last != pais.getPaiType() / 10) {
return false;
}
}
index++;
}
boolean flag3 = false;
//int memberPai[][]  [0][]万		[1][]筒 		[2][]索  		[3][]风
//int last  牌的类型 万 1 	筒 2    	索 3       风4	 将牌转换为二维数组
if (memberPai[3][0] == 0) {
if (memberPai[0][0] == 0 && memberPai[1][0] == 0) {
if (last > 0 && last != 3) {
return false;
}
flag3 = true;
} else if (memberPai[0][0] == 0 && memberPai[2][0] == 0) {
if (last > 0 && last != 2) {
return false;
}
flag3 = true;
} else if (memberPai[1][0] == 0 && memberPai[2][0] == 0) {
if (last > 0 && last != 1) {
return false;
}
flag3 = true;
}
}
return flag3;
}


*//**
	 * 混一色
	 * 
	 * @param memberPai
	 * @param showPai
	 * @return
	 */
/*
public static boolean isHunYiSe(int[][] memberPai, List<Builder> showPai) {
int index = 0;
int last = 0; 
boolean hasFeng = false;
for (GPaiQiang.Builder pais : showPai) {
	if (pais.getType() == OptionsType.GUDING_GANG
			|| pais.getType() == OptionsType.LAIZI_GANG) {
		continue;
	}
	if (pais.getPaiType() / 10==4) {
		hasFeng = true;
		continue;
	}
	if (index == 0) {
		last = pais.getPaiType() / 10;
	} else {
		if (last != pais.getPaiType() / 10) {
			return false;
		}
	}
	index++;
}
if(!hasFeng){
	if (memberPai[3][0] == 0) {
		return false;
	}
}
boolean flag3 = false;
if (memberPai[0][0] == 0 && memberPai[1][0] == 0) {
	if (last > 0 && last != 3) {
		return false;
	}
	flag3 = true;
} else if (memberPai[0][0] == 0 && memberPai[2][0] == 0) {
	if (last > 0 && last != 2) {
		return false;
	}
	flag3 = true;
} else if (memberPai[1][0] == 0 && memberPai[2][0] == 0) {
	if (last > 0 && last != 1) {
		return false;
	}
	flag3 = true;
}
return flag3;
}


*//**
	 * yitiaolong
	 * 
	 * @param memberPai
	 * @param showPai
	 * @return
	 */
/*
public static boolean isYiTiaoLong(int[][] memberPai) {
for (int i = 0; i < 3; i++) {
	boolean flag = true;
	for (int j = 1; j <= 9; j++) {
		if (memberPai[i][j] < 1) {
			flag = false;
			break;
		}
	}
	if (flag) {
		return true;
	}
}

return false;
}



*//**
	 * 不能有癞子 判断全求人能不能胡
	 * 
	 * @param memberPai
	 * @param showPai
	 * @return
	 */
/*
private static boolean isErRight(int[][] memberPai, List<Builder> showPai) {
int size = memberPai[0][0] + memberPai[1][0] + memberPai[1][0]
		+ memberPai[1][0];
if (size > 2) {
	return true;
}

// 碰碰胡不能吃，吃了就不是碰碰胡了
boolean flag1 = true;//碰碰
boolean flag2 = true;//清一色
int index = 0;
int last = 0;
for (GPaiQiang.Builder pais : showPai) {
	if(index==0){
		last = pais.getPaiType()%10;		
	}else{
		if(last!=pais.getPaiType()%10){
			flag2 = false;
		}
	}
	if (pais.getType() == OptionsType.CHI) {
		flag1 = false;
	}
	index++;
}
boolean flag3 = false;
if (memberPai[3][0] == 0) {
	if (memberPai[0][0] == 0 && memberPai[1][0] == 0) {
		flag3 = true;
	}else if (memberPai[0][0] == 0 && memberPai[2][0] == 0) {
		flag3 = true;
	}else if (memberPai[1][0] == 0 && memberPai[2][0] == 0) {
		flag3 = true;
	}
} 	
if(flag2&&flag3){
	return true;
}
if(flag1){
	return true;
}
for (int i = 0; i < 4; i++) {
	for (int j = 1; j <= 9; j++) {
		if(memberPai[i][j]>0&&j%3!=2){
			return false;
		}
	}
}

return true;
}

*//**
	 * 分析每一种牌是否符合
	 *
	 * @param aKindPai
	 * @return
	 */
/*
public static boolean analyze(int[] aKindPai, boolean ziPai) {

if (aKindPai[0] == 0) {
	return true;
}
int index = 0;
for (int i = 1; i <= 9; i++) {
	if (aKindPai[i] != 0) {
		index = i;
		break;
	}
}
boolean result = false;
if (aKindPai[index] >= 3) {
	aKindPai[index] = aKindPai[index] - 3;
	aKindPai[0] = aKindPai[0] - 3;
	//int[] temp = new int[10];
	// System.arraycopy(aKindPai, 0, temp, 0, aKindPai.length);
	result = analyze(aKindPai, ziPai);
	aKindPai[index] = aKindPai[index] + 3;
	aKindPai[0] = aKindPai[0] + 3;
	return result;
}
if (!ziPai&&index < 8 && aKindPai[index + 1] > 0
		&& aKindPai[index + 2] > 0) {
	aKindPai[index] = aKindPai[index] - 1;
	aKindPai[index + 1] = aKindPai[index + 1] - 1;
	aKindPai[index + 2] = aKindPai[index + 2] - 1;
	aKindPai[0] = aKindPai[0] - 3;
	//int[] temp = new int[10];
	// System.arraycopy(aKindPai, 0, temp, 0, aKindPai.length);
	result = analyze(aKindPai, ziPai);
	aKindPai[index] = aKindPai[index] + 1;
	aKindPai[index + 1] = aKindPai[index + 1] + 1;
	aKindPai[index + 2] = aKindPai[index + 2] + 1;
	aKindPai[0] = aKindPai[0] + 3;
	return result;
}
if (ziPai && ziShun) {
	// 东西南北
	// 中发白
	if (index < 6 && aKindPai[index + 1] > 0 && aKindPai[index + 2] > 0) {
		aKindPai[index] = aKindPai[index] - 1;
		aKindPai[index + 1] = aKindPai[index + 1] - 1;
		aKindPai[index + 2] = aKindPai[index + 2] - 1;
		aKindPai[0] = aKindPai[0] - 3;
		// int[] temp = new int[10];
		// System.arraycopy(aKindPai, 0, temp, 0, aKindPai.length);
		result = analyze(aKindPai, ziPai, ziShun);
		aKindPai[index] = aKindPai[index] + 1;
		aKindPai[index + 1] = aKindPai[index + 1] + 1;
		aKindPai[index + 2] = aKindPai[index + 2] + 1;
		aKindPai[0] = aKindPai[0] + 3;
		return result;
	}

} else {
	
}

return false;
}

*//**
	 * 分析每一种牌是否符合
	 *
	 * @param aKindPai
	 * @return
	 */
/*
public static boolean analyzeShun(int[] aKindPai, boolean ziPai) {

if (aKindPai[0] == 0) {
	return true;
}

if(ziPai){
	// 东西南北
	int index = 0;
	for (int i = 1; i <= 4; i++) {
		if (aKindPai[i] != 0) {
			index = i;
			break;
		}
	}
	boolean result = false;
	if (aKindPai[index] == 4) {
		aKindPai[index] = aKindPai[index] - 3;
		aKindPai[0] = aKindPai[0] - 3;
		result = analyzeShun(aKindPai, ziPai);
		aKindPai[index] = aKindPai[index] + 3;
		aKindPai[0] = aKindPai[0] + 3;
		return result;
	}
	if (aKindPai[index] == 3) {
		//TODO 有可能 纯粹顺子非刻
		aKindPai[index] = aKindPai[index] - 3;
		aKindPai[0] = aKindPai[0] - 3;
		result = analyzeShun(aKindPai, ziPai);
		aKindPai[index] = aKindPai[index] + 3;
		aKindPai[0] = aKindPai[0] + 3;
		if(result){
			return result;
		}	
	}
	if (index ==1) {
		//东要处理完所有逆顺子
		//正顺序
		if (aKindPai[index + 1] > 0 && aKindPai[index + 2] > 0) {
			aKindPai[index] = aKindPai[index] - 1;
			aKindPai[index + 1] = aKindPai[index + 1] - 1;
			aKindPai[index + 2] = aKindPai[index + 2] - 1;
			aKindPai[0] = aKindPai[0] - 3;
			result = analyzeShun(aKindPai, ziPai);
			aKindPai[index] = aKindPai[index] + 1;
			aKindPai[index + 1] = aKindPai[index + 1] + 1;
			aKindPai[index + 2] = aKindPai[index + 2] + 1;
			aKindPai[0] = aKindPai[0] + 3;
			if(result){
				return result;
			}		
		}
		//逆顺序
		if (aKindPai[index + 1] > 0 && aKindPai[index + 2] > 0) {
			aKindPai[index] = aKindPai[index] - 1;
			aKindPai[index + 1] = aKindPai[index + 1] - 1;
			aKindPai[index + 2] = aKindPai[index + 2] - 1;
			aKindPai[0] = aKindPai[0] - 3;
			result = analyzeShun(aKindPai, ziPai);
			aKindPai[index] = aKindPai[index] + 1;
			aKindPai[index + 1] = aKindPai[index + 1] + 1;
			aKindPai[index + 2] = aKindPai[index + 2] + 1;
			aKindPai[0] = aKindPai[0] + 3;
			if(result){
				return result;
			}		
		}	
	}else{
		//西
		if (index < 3 && aKindPai[index + 1] > 0 && aKindPai[index + 2] > 0) {
			aKindPai[index] = aKindPai[index] - 1;
			aKindPai[index + 1] = aKindPai[index + 1] - 1;
			aKindPai[index + 2] = aKindPai[index + 2] - 1;
			aKindPai[0] = aKindPai[0] - 3;
			result = analyzeShun(aKindPai, ziPai);
			aKindPai[index] = aKindPai[index] + 1;
			aKindPai[index + 1] = aKindPai[index + 1] + 1;
			aKindPai[index + 2] = aKindPai[index + 2] + 1;
			aKindPai[0] = aKindPai[0] + 3;
			return result;
		}		
	}
	
	index = 0;
	for (int i = 5; i <= 7; i++) {
		if (aKindPai[i] != 0) {
			index = i;
			break;
		}
	}
	
	// 中发白
	if (aKindPai[index] >= 3) {
		aKindPai[index] = aKindPai[index] - 3;
		aKindPai[0] = aKindPai[0] - 3;
		result = analyzeShun(aKindPai, ziPai);
		aKindPai[index] = aKindPai[index] + 3;
		aKindPai[0] = aKindPai[0] + 3;
		return result;
	}
	if (index<6&&aKindPai[index + 1] > 0
			&& aKindPai[index + 2] > 0) {
		aKindPai[index] = aKindPai[index] - 1;
		aKindPai[index + 1] = aKindPai[index + 1] - 1;
		aKindPai[index + 2] = aKindPai[index + 2] - 1;
		aKindPai[0] = aKindPai[0] - 3;
		result = analyzeShun(aKindPai, ziPai);
		aKindPai[index] = aKindPai[index] + 1;
		aKindPai[index + 1] = aKindPai[index + 1] + 1;
		aKindPai[index + 2] = aKindPai[index + 2] + 1;
		aKindPai[0] = aKindPai[0] + 3;
		return result;
	}			
}else{
	int index = 0;
	for (int i = 1; i <= 9; i++) {
		if (aKindPai[i] != 0) {
			index = i;
			break;
		}
	}
	boolean result = false;
	if (aKindPai[index] >= 3) {
		aKindPai[index] = aKindPai[index] - 3;
		aKindPai[0] = aKindPai[0] - 3;
		result = analyzeShun(aKindPai, ziPai);
		aKindPai[index] = aKindPai[index] + 3;
		aKindPai[0] = aKindPai[0] + 3;
		return result;
	}
	if (index < 8 && aKindPai[index + 1] > 0
			&& aKindPai[index + 2] > 0) {
		aKindPai[index] = aKindPai[index] - 1;
		aKindPai[index + 1] = aKindPai[index + 1] - 1;
		aKindPai[index + 2] = aKindPai[index + 2] - 1;
		aKindPai[0] = aKindPai[0] - 3;
		//int[] temp = new int[10];
		// System.arraycopy(aKindPai, 0, temp, 0, aKindPai.length);
		result = analyze(aKindPai, ziPai);
		aKindPai[index] = aKindPai[index] + 1;
		aKindPai[index + 1] = aKindPai[index + 1] + 1;
		aKindPai[index + 2] = aKindPai[index + 2] + 1;
		aKindPai[0] = aKindPai[0] + 3;
		return result;
	}
}

return false;
}

*//**
	 * 杠
	 *
	 * @param allPai
	 * @param pai
	 * @param type
	 * @return
	 */
/*
public static boolean gangPai(int[][] allPai, int pai, int type) {

int idx = pai / 10;
int pos = pai % 10;

switch (type) {
case 1: {// 暗杠
	int yetPaiNum = allPai[idx - 1][pos];
	return yetPaiNum == 4;
}
case 2: {// 明杠
	int yetPaiNum = allPai[idx - 1][pos];
	return yetPaiNum == 3;
}
}
return false;
}

public boolean huPai(int[][] allPai, int laizi) {

int wang = allPai[0][0];
int tong = allPai[1][0];
int tiao = allPai[2][0];
if (wang > 0 && tong > 0 && tiao > 0) {
	// 花猪不能胡牌
	return false;
}
int allPaiNum = wang + tong + tiao + laizi;
if (allPaiNum % 3 != 2) {
	// 不能满足3n+2的数量要求
	return false;
}

int jiangPos = 0;// 哪一组牌中存在将
int yuShu = 0;
boolean jiangExisted = false;
for (int i = 0; i < 3; i++) {
	yuShu = allPai[i][0] % 3;
	if (yuShu == 1) {
		return false;
	}
	if (yuShu == 2) {
		if (jiangExisted) {
			return false;
		}
		jiangPos = i;
		jiangExisted = true;
	}
}

if (wang > 0) {
	int[] temp = new int[10];
	for (int j = 0; j <= 9; j++) {
		// temp[j] = allPai[i][j];
	}
	if (!analyze(temp, false)) {
		return false;
	}
}

boolean success = false;
for (int j = 1; j <= 9; j++) {
	if (allPai[jiangPos][j] >= 2) {
		allPai[jiangPos][j] = allPai[jiangPos][j] - 2;
		allPai[jiangPos][0] = allPai[jiangPos][0] - 2;

		int[] temp = new int[10];
		for (int k = 0; k <= 9; k++) {
			temp[k] = allPai[jiangPos][k];
		}
		if (analyze(temp, false)) {
			success = true;
		}
		allPai[jiangPos][j] = allPai[jiangPos][j] + 2;
		allPai[jiangPos][0] = allPai[jiangPos][0] + 2;
		if (success) {
			break;
		}
	}
}
return success;
}

//判断碰碰胡
public static boolean judgePPH(int[][] allPai,int laizi,List<GPaiQiang.Builder> showPai) {
Integer yu = laizi / 10 - 1;
Integer mod = laizi % 10;
int laiNum = allPai[yu][mod];

//碰碰胡不能吃，吃了就不是碰碰胡了
for (GPaiQiang.Builder pais : showPai) {
	if (pais.getType() == OptionsType.CHI) {
		return false;
	}
}
List<Integer> list = new ArrayList<>();
// 求单
for (int i = 0; i < 4; i++) {
	for (int j = 1; j <= 9; j++) {
		if (i == yu && j == mod) {
			continue;
		}
		if (allPai[i][j]>0&&allPai[i][j] != 3) {
			list.add((i+1)*10+j);
		}
		if (allPai[i][j]>0&&allPai[i][j] == 4) {
			return false;
		}
	}
}
// 各种碰碰胡
if (laiNum == 0 && list.size() == 1) {
	return true;
}
if (laiNum == 1 && list.size() == 1) {
	return true;
}
if (laiNum == 1 && list.size() == 2) {
	return true;
}
if (laiNum == 2 && list.size() == 0) {
	return true;
}
// 2 1 +1 2
if (laiNum == 2 && list.size() == 2) {
	return true;
}
if (laiNum == 3 && list.size() == 1) {
	return true;
}
// 1 1 + 1 2 2 +2 2 2 2 + 2 2 1
if (laiNum == 3 && list.size() < 3) {
	return true;
}
if (laiNum == 4 && list.size() < 4) {
	return true;
}
if (laiNum > 0) {
	int rest = laiNum;
	for (Integer data : list) {
		int num = allPai[data / 10 - 1][data % 10];
		rest = rest - (3 - num);
	}
	if (rest > -2) {
		return true;
	}
}

return false;
}


*//**
	 * 全求人的特点是二张和258将
	 * 
	 * @param shouPai
	 * @param laiZi
	 * @return
	 */
/*
public static boolean judgeQQR(List<Integer> shouPai, int laiZi) {
if (shouPai.size() == 2) {
	for (Integer data : shouPai) {
		if (data.equals(laiZi)) {
			continue;
		}
		if (data / 10 == 4) {
			return false;
		}
		int modValue = data % 10;
		
		if (modValue % 3 != 2) {
			return false;
		}
	}
	return true;
}
return false;
}


*//**
	 * 全求人的特点是二张和258将
	 * 
	 * @param shouPai
	 * @param laiZi
	 * @return
	 */
/*
public static boolean judgeSDQQR(List<Integer> shouPai, int laiZi) {
if (shouPai.size() == 2) {
	for (Integer data : shouPai) {
		if (data.equals(laiZi)) {
			continue;
		}
		if (data / 10 == 4) {
			return false;
		}
	}
	return true;
}
return false;
}
*//**
	 * 注：清一色(整副牌,包括吃、碰、杠) 原理：判断第一张牌和剩余牌的首个数字(1为万,2为筒,3为索)
	 * 
	 * @param list传所有牌
	 * @return
	 */
/*
public static boolean judgeFlush(List<Integer> list, int laiZi) {
int fistChar = 0;
for (Integer integer : list) {
	if (integer != laiZi) {
		//取出除癞子的第一张牌的所属花色  （13代表3万，取出1，代表花色万）
		fistChar = integer/10;
		break;
	}
}
for (Integer integer : list) {
	if (integer == laiZi) {
		continue;
	}
	if(integer>40){
		return false;
	}
	if (fistChar!=integer/10) {
		return false;
	}
}
return true;
}	

*//**
	 * 大对子 11、111、111、111、111的牌型(有过碰,杠的牌都算,不需要考虑碰，杠)
	 * 判断原理：1张false,2张只能出现一次（避免七对胡）,4张false,剩余为3张
	 * 
	 * @param list传剩余手牌
	 * @return
	 */
/*
public static boolean judgeFourTriple(List<Integer> list) {
HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
for (Integer integer : list) {
	if (hm.get(integer) == null) {
		hm.put(integer, 1);
	} else {
		hm.put(integer, hm.get(integer) + 1);
	}
}
int twoNum = 0;
for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
	// 只能出现2张或3张
	if (entry.getValue() == 2 || entry.getValue() == 3) {
		if (entry.getValue() == 2) {
			twoNum++;
			if (twoNum >= 2) {
				// 2张只能出现一次
				return false;
			}
		}
	} else {
		return false;
	}
}
return true;
}

*//**
	 * 巧7对11、22、33、44、55、66、77 原理：手上的牌需要14张 7对子必然有7张不同的牌
	 * 
	 * @param list传手牌
	 * @return
	 */
/*
public static boolean judegeSevenPairs(List<Integer> list) {
if (list.size() == 14) {
	HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
	for (Integer integer : list) {
		if (hm.get(integer) == null) {
			hm.put(integer, 1);
		} else {
			hm.put(integer, hm.get(integer) + 1);
		}
	}
	// 遍历map
	for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
		if (entry.getValue() != 2) {
			return false;
		}
	}
}
return true;
}

*//**
	 * 龙7对：11、22、33、44、55、6666(14张牌全是对子，其中还要有四张一样的)
	 * 
	 * @param list传手牌
	 * @return
	 */
/*
public static boolean judegeLongSevenPairs(List<Integer> list) {
if (list.size() == 14) {
	HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
	for (Integer integer : list) {
		if (hm.get(integer) == null) {
			hm.put(integer, 1);
		} else {
			hm.put(integer, hm.get(integer) + 1);
		}
	}
	for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
		if (entry.getValue() % 2 != 0) {
			return false;
		}
	}
	for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
		if (entry.getValue() == 4) {
			return true;
		}
	}
	return false;
} else {
	return false;
}
}


// 十三烂:手中十四张牌中，序数牌间隔大于等于3，字牌没有重复所组成的牌形
// 例如（三万，六万，九万，一条，四条，七条，二饼，五饼，八饼，东，南，中，发，白）此牌型可形成胡牌。
// 原始牌型，癞子进行额外处理
// fithu不支持list，只能转成数组
public static boolean judgeShiSanLan(int[][] shouPaiArr, List<GPaiQiang.Builder> paiQiangList) {
if (paiQiangList.size() > 0) {
	return false;
}
int[][] allPai = shouPaiArr.clone();
int pai = 0;
for (int i = 0; i < allPai.length; i++) {
	pai = 0;
	for (int j = 1; j < allPai[i].length; j++) {
		int temp = (i + 1) * 10 + j;
		if (allPai[i][j] == 0) {
			continue;
		}
		if (allPai[i][j] >= 2) {
			return false;
		}
		if (i < 3) {
			if (allPai[i][j] > 0) {
				if (pai == 0) {// 第一次出现万或筒或条
					pai = temp;
				} else {
					if (temp - pai < 3) {
						return false;
					}
					pai = temp;
				}
			}
		}
	}
}
return true;
}

// 在十三烂的基础上再进行判断7字全
// 7字全
public static boolean judgeQiZiQuan(int[][] shouPaiArr, List<GPaiQiang.Builder> paiQiangList) {
if (paiQiangList.size() > 0) {
	return false;
}
int[][] allPai = shouPaiArr.clone();
for (int j = 1; j <=7; j++) {
	if (allPai[3][j] == 0) {
		return false;
	}
}
return true;
}

// 幺仁
// 东，南，西，北，中，发，白，1万，9万，1条，9条 ，1饼，9饼
// (可吃，可顺，可碰，可杠) 判断牌墙
public static boolean judgeYaoRen(List<Integer> shouPai, List<GPaiQiang.Builder> showPai) {
for (GPaiQiang.Builder paiQiang : showPai) {
	if (paiQiang.getType() == OptionsType.CHI) {// 吃
		if (paiQiang.getPaiType() < 40) {
			return false;
		}
	} else {// 碰，明杠，冲杠，暗杠
		if (paiQiang.getPaiType() < 40) {
			int pai = paiQiang.getPaiType();
			Integer mod = pai % 10;
			if (mod != 1 || mod != 9) {
				return false;
			}
		}
	}
}
// 判断手牌的逻辑
for (int pai : shouPai) {
	Integer mod = pai % 10;
	if (mod != 1 || mod != 9) {
		return false;
	}
}
return true;
}

// 判断一条龙（变牌），有吃碰杠也行（万饼条某一单色，形成123456789+任意顺或刻牌＋将牌即可成胡）
// list是变了的牌，把牌放进数组里
public static boolean judgeOneLong(List<Integer> list, List<GPaiQiang.Builder> showPai) {
int[][] allPai = new int[4][10];
for (Builder builder : showPai) {
	GPaiInfo paiInfo = builder.getPai();
	for (int pai : paiInfo.getPaiList()) {
		Integer yu = pai / 10 - 1;
		Integer mod = pai % 10;
		allPai[yu][0] += 1;
		allPai[yu][mod] += 1;
	}
}
for (int pai : list) {
	Integer yu = pai / 10 - 1;
	Integer mod = pai % 10;
	allPai[yu][0] += 1;
	allPai[yu][mod] += 1;
}
// 遍历牌数组，进行判断
boolean flag = false;
for (int i = 0; i < allPai.length; i++) {
	flag = false;
	for (int j = 0; j < allPai[i].length; j++) {
		if (allPai[i][0] < 9) {
			break;
		}
		if (allPai[i][j] == 0) {
			return false;
		}
		flag=true;
	}
	if (flag) {
		return true;
	}
}
return flag;
}

// 判断十四幺（fithu不支持list，只能转成数组）
//	public static boolean judgeShiSanYao(int[][] shouPaiArr, List<GPaiQiang.Builder> paiQiangList, int laiZi) {
//		if (paiQiangList.size() > 0) {
//			return false;
//		}
//		List<Integer> shiSanList = new ArrayList<>();
//		int[] shiSanArr = new int[] { 11, 19, 21, 29, 31, 39, 41, 42, 43, 44, 45, 46, 47 };
//		for (Integer pai : shiSanArr) {
//			shiSanList.add(pai);
//		}
//		int yu = laiZi / 10 - 1;
//		int mod = laiZi % 10;
//		int[][] allPai = shouPaiArr.clone();
//		// 要有一个对子，癞子可能为其中一个
//		boolean hasJiang = false;
//		for (int i = 0; i < allPai.length; i++) {
//			for (int j = 1; j < allPai[i].length; j++) {
//				int temp = (i + 1) * 10 + j;
//				if (allPai[i][j] == 0) {
//					continue;
//				}
//				if (temp == laiZi) {
//					continue;
//				}
//				if (!shiSanList.contains(temp)) {
//					return false;
//				}
//				if (allPai[i][j] > 2) {
//					return false;
//				}
//				if (allPai[i][j] == 2) {
//					if (!hasJiang) {
//						hasJiang = true;
//					} else {
//						return false;
//					}
//				}
//			}
//		}
//		return true;
//	}



*//**
	 * 判断勾胡（判断show牌）
	 * 
	 * @return
	 */
/*
public static boolean judegeWithGang(List<GPaiQiang.Builder> map) {
for (GPaiQiang.Builder entry : map) {
if (entry.getPai().getPaiCount()>=4) {
		return true;
	}
}
return false;
}

public static Integer moPai(List<Integer> shouPaiList,List<Integer> pais){
Integer moPai=0;
List<Integer> pai = shouPaiList;
List<Integer> tablePais = pais;
List<Integer> randomList=new ArrayList<Integer>();
randomList=moPaiOne(shouPaiList);
moPai=fitMoPai(pai,randomList,tablePais);
if(moPai!=0){
	return moPai;
}
randomList.clear();
randomList=moPaiTwo(shouPaiList);
moPai=fitMoPai(pai,randomList,tablePais);
if(moPai!=0){
	return moPai;
}
randomList.clear();
randomList=moPaiThree(shouPaiList);
moPai=fitMoPai(pai,randomList,tablePais);
if(moPai!=0){
	return moPai;
}
randomList.clear();
randomList=moPaiFour(shouPaiList);
moPai=fitMoPai(pai,randomList,tablePais);
if(moPai!=0){
	return moPai;
}
randomList.clear();
randomList=moPaiFive(shouPaiList);
moPai=fitMoPai(pai,randomList,tablePais);
if(moPai!=0){
	return moPai;
}
return moPai;
}

//给玩家随机发一个手上牌数最多的那个牌型的牌，如果牌堆里面没有这种牌，则给玩家随机发一个手上牌数第二多的那个牌型的牌
public static Integer randomMoPai(List<Integer> shouPaiList, List<Integer> tablePais){
Integer moPai = 0;
//得到玩家各种牌型的总数 排序  选第一 第二
int[][] shouPaiArr = ZTMahJongRule.conversionType(shouPaiList);
// 手牌的数量
int wangShouNum = shouPaiArr[0][0];
int tongShouNum = shouPaiArr[1][0];
int tiaoShouNum = shouPaiArr[2][0];
int fengShouNum = shouPaiArr[3][0];

int wang = 1;
int tong = 2;
int tiao = 3;
int feng = 4;

HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
hm.put(wang, wangShouNum);
hm.put(tong, tongShouNum);
hm.put(tiao, tiaoShouNum);
hm.put(feng, fengShouNum);
// 对hm进行排序返回
Map<Integer, Integer> hm2 = ZTMahJongRule.sortMapByValue(hm);
// list存储table牌 万，筒，条剩余的数量
List<Integer> wanglist = new ArrayList<Integer>();
List<Integer> tonglist = new ArrayList<Integer>();
List<Integer> tiaolist = new ArrayList<Integer>();
List<Integer> fenglist = new ArrayList<Integer>();

for (Integer integer : tablePais) {
		if (integer >= 11 && integer < 20) {
			wanglist.add(integer);
		} else if (integer >= 21 && integer < 30) {
			tonglist.add(integer);
		} else if (integer >= 31 && integer < 40) {
			tiaolist.add(integer);
		}else{
			fenglist.add(integer);
		}
}
int times = 0;//2次
for (Entry<Integer, Integer> entry : hm2.entrySet() ) {
	if (times == 2) {
		break;
	}
	Integer key = entry.getKey();// 键值
	switch (key) {
	case 1:
		if (wanglist.size() > 0) {
			moPai = Probability.getRand(wanglist);
		}
		break;
	case 2:
		if (tonglist.size() > 0) {
			moPai = Probability.getRand(tonglist);
		}
		break;
	case 3:
		if (tiaolist.size() > 0) {
			moPai = Probability.getRand(tiaolist);
		}
		break;
	case 4:
		if (fenglist.size() > 0) {
			moPai = Probability.getRand(fenglist);
		}
		break;
	default:
		break;
	}
	if (moPai != 0) {
		return moPai;
	}
	times++;
}
//
return moPai;
}
public static Integer fitMoPai(List<Integer> shouPaiList,List<Integer> randomList,List<Integer> tablePais){		
Integer moPai=0;	
int[][] shouPaiArr = ZTMahJongRule.conversionType(shouPaiList);
// 手牌最大的数量
int wangShouNum = shouPaiArr[0][0];
int tongShouNum = shouPaiArr[1][0];
int tiaoShouNum = shouPaiArr[2][0];
int fengShouNum = shouPaiArr[3][0];
int wang = 1;
int tong = 2;
int tiao = 3;
int feng = 4;
HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
hm.put(wang, wangShouNum);
hm.put(tong, tongShouNum);
hm.put(tiao, tiaoShouNum);
hm.put(feng, fengShouNum);
// 对hm进行排序返回
Map<Integer, Integer> hm2 = ZTMahJongRule.sortMapByValue(hm);
// list存储万，筒，条的数量
List<Integer> wanglist = new ArrayList<Integer>();
List<Integer> tonglist = new ArrayList<Integer>();
List<Integer> tiaolist = new ArrayList<Integer>();
List<Integer> fenglist = new ArrayList<Integer>();
for (Integer integer : tablePais) {
	if(randomList.contains(integer)){
		if (integer >= 11 && integer < 20) {
			wanglist.add(integer);
		} else if (integer >= 21 && integer < 30) {
			tonglist.add(integer);
		} else if (integer >= 31 && integer < 40) {
			tiaolist.add(integer);
		} else if (integer >= 41 && integer < 50) {
			fenglist.add(integer);
		}
	}
}

System.out.println("万" + wanglist);
System.out.println("筒" + tonglist);
System.out.println("条" + tiaolist);
System.out.println("fengf" + fenglist);
for (Map.Entry<Integer, Integer> entry : hm2.entrySet()) {
	Integer key = entry.getKey();// 键值
	switch (key) {
	case 1:
		if (wanglist.size() > 0) {
			moPai = Probability.getRand(wanglist);
		}
		break;
	case 2:
		if (tonglist.size() > 0) {
			moPai = Probability.getRand(tonglist);
		}
		break;
	case 3:
		if (tiaolist.size() > 0) {
			moPai = Probability.getRand(tiaolist);
		}
		break;
	case 4:
	if (fenglist.size() > 0) {
			moPai = Probability.getRand(fenglist);
	}
	break;
	default:
		break;
	}
}
return moPai;
}

// 如 4 6筒中的缺五筒 1 3万中缺二万 （卡牌） 没有听牌的套路发牌1
public static List<Integer> moPaiOne(List<Integer> shouPaiList) {
List<Integer> randomList = new ArrayList<Integer>();
List<Integer> pai = shouPaiList;
int[][] paiArr = ZTMahJongRule.conversionType(pai);
// 缺牌list
List<Integer> quePai = ZTMahJongRule.quePai((paiArr),false);
for (Integer integer : quePai) {
	int front = integer / 10 - 1;
	int next = integer % 10;
	int num = paiArr[front][next];
	//System.out.println("front=" + front + "next=" + next + "num" + num);
	// 分别是1条，1万，1筒，9条，9万，9筒不符合
	if (next>0&&next != 1 && next != 9) {
		if (paiArr[front][next - 1] >= 1
				&& paiArr[front][next + 1] >= 1) {
			//System.out.println("符合" + integer);
			randomList.add(integer);
		}
	}
}
return randomList;
}


// 如89筒缺7筒，12筒缺3筒，条万同理（偏章）没有听牌的套路发牌2
public static List<Integer> moPaiTwo(List<Integer> shouPaiList) {
List<Integer> randomList = new ArrayList<Integer>();
List<Integer> pai = shouPaiList;
int[][] paiArr = ZTMahJongRule.conversionType(pai);
// 缺牌list
List<Integer> quePai = ZTMahJongRule.quePai((paiArr),false);
for (Integer integer : quePai) {
	int front = integer / 10 - 1;
	int next = integer % 10;
	int num = paiArr[front][next];
	//System.out.println("front=" + front + "next=" + next + "num" + num);
	// 只有3筒，7筒，3万，7万， 3条，7条符合要求
	if (next == 3) {
		if (paiArr[front][next - 1] >= 1
				&& paiArr[front][next - 2] >= 1) {
			System.out.println("符合" + integer);
			randomList.add(integer);
		}
	} else if (next == 7) {
		if (paiArr[front][next + 1] >= 1
				&& paiArr[front][next + 2] >= 1) {
			System.out.println("符合" + integer);
			randomList.add(integer);
		}
	}
}
return randomList;
}

// 偏章，5689筒缺4，7筒，1245筒缺3，6筒，同时去掉偏章89筒和12筒的影响
public static List<Integer> moPaiThree(List<Integer> shouPaiList) {
List<Integer> randomList = new ArrayList<Integer>();
List<Integer> pai = shouPaiList;
int[][] paiArr = ZTMahJongRule.conversionType(pai);
// 所缺的牌list
List<Integer> quePai = ZTMahJongRule.quePai((paiArr),false);
for (Integer integer : quePai) {
	int front = integer / 10 - 1;
	int next = integer % 10;
	int num = paiArr[front][next];
	//System.out.println("front=" + front + "next=" + next + "num" + num);
	if (next == 1 || next == 2 || next == 3) {
		if (paiArr[front][next + 1] >= 1 && paiArr[front][next + 2] > 0) {
			//System.out.println("符合" + integer);
			randomList.add(integer);
		}
	} else if (next == 7 || next == 8 || next == 9) {
		if (paiArr[front][next - 1] > 0 && paiArr[front][next - 2] > 0) {
			//System.out.println("符合" + integer);
			randomList.add(integer);
		}
	} else {
		if ((paiArr[front][next - 1] > 0 && paiArr[front][next - 2] > 0)
				|| paiArr[front][next + 1] >= 1
				&& paiArr[front][next + 2] > 0) {
			//System.out.println("符合" + integer);
			randomList.add(integer);
		}
	}
}
return randomList;
}

// 寻找2张一样的
public static List<Integer> moPaiFour(List<Integer> shouPaiList) {
List<Integer> randomList = new ArrayList<Integer>();
List<Integer> pai = shouPaiList;
int[][] paiArr = ZTMahJongRule.conversionType(pai);
for (int i = 0; i < 3; i++) {
	for (int j = 1; j <= 9; j++) {
		if (paiArr[i][j] == 2) {
			Integer Integer = 10 * (1 + i) + j;
			System.out.println("测试five" + Integer);
			randomList.add(Integer);
		}
	}
}
return randomList;
}

// 寻找3张一样的
public static List<Integer> moPaiFive(List<Integer> shouPaiList) {
List<Integer> pai = shouPaiList;
List<Integer> randomList = new ArrayList<Integer>();
int[][] paiArr = ZTMahJongRule.conversionType(pai);
for (int i = 0; i < 3; i++) {
	for (int j = 1; j <= 9; j++) {
		if (paiArr[i][j] == 3) {
			Integer Integer = 10 * (1 + i) + j;
			System.out.println("测试five" + Integer);
			randomList.add(Integer);
		}
	}
}
return randomList;
}

*//**
	 * 李培光 针对麻将规则的排序 使用 Map按value进行排序 未封装成泛型
	 * 
	 * @param oriMap
	 * @return
	 */
/*
public static Map<Integer, Integer> sortMapByValue(Map<Integer, Integer> oriMap) {
if (oriMap == null || oriMap.isEmpty()) {
	return null;
}
Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
List<Map.Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(
		oriMap.entrySet());
Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {			
	public int compare(Entry<Integer, Integer> me1, Entry<Integer, Integer> me2) {
		//两个值相等则进行随机排序
		if(me2.getValue()==me1.getValue()){
			int num=MathUtil.randomNumber(0, 1);
			if(num==0){
				return me1.getValue().compareTo(me2.getValue());
			}else{
				return me2.getValue().compareTo(me1.getValue());
			}
		}else{
			return me2.getValue().compareTo(me1.getValue());
		}		
	}
});
Iterator<Map.Entry<Integer, Integer>> iter = entryList.iterator();
Map.Entry<Integer, Integer> tmpEntry = null;
while (iter.hasNext()) {
	tmpEntry = iter.next();
	sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
}
return sortedMap;
}


*//**
	 * 获取正常出牌情况下 下一的出牌座位 剔除胡牌
	 *
	 * @return
	 */
/*
public static int getNextPlaySeat(int size,List<Integer> winners,int lastPlaySeat) {
List<Integer> list = new ArrayList<Integer>();
for (int i = 1; i <= size; i++) {
	if (!winners.contains(i)) {
		list.add(i);
	}
}
Integer seat = lastPlaySeat;
int index = list.indexOf(seat);
if (index == -1) {
	index = 0;
}
if (index == list.size() - 1) {

	return list.get(0);
} else {
	if (list.size() == 1) {
		return list.get(0);
	} else if (list.size() == 0) {
		return 0;
	} else {
		return list.get(index + 1);
	}
}

}


*//**
	 * 获取正常出牌情况下 上一的出牌座位 剔除胡牌
	 *
	 * @return
	 */
/*
public static int getLastPlaySeat(int size,int lastPlaySeat) {
List<Integer> list = new ArrayList<Integer>();
for (int i = 1; i <= size; i++) {
	list.add(i);
}
Integer seat = lastPlaySeat;
int index = list.indexOf(seat);
if (index == -1) {
	index = 0;
}
if (index == 0) {

	return list.get(list.size() - 1);
} else {
	if (list.size() == 1) {
		return list.get(0);
	} else if (list.size() == 0) {
		return 0;
	} else {
		return list.get(index - 1);
	}
}

}

public static void permutation(List<Integer> s, int u[], int o[], int n, int m,
	int p, List<List<Integer>> data) {
//System.out.println(s+":"+n+":"+m);
int i;
if (p == m)  达到所选则的数目 
{
	for (i = 0; i < m; i++) {
		//System.out.print(o[i]);
		data.get(data.size() - 1).add(o[i]);
	}
	data.add(new ArrayList<Integer>());
	//System.out.println();
} else {
	for (i = 0; i < n; i++) {
		if (u[i] != m)  如当前元素为达到使用次数即m次 
		{
			u[i] += 1;
			o[p] = s.get(i);
			permutation(s, u, o, n, m, p + 1, data);
			u[i] -= 1;
		}
	}
}
}
*//**
	 * 重复组合
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
/*
public static List<List<Integer>> cacheCombine(int n, int m){
String key = n+"_"+m;
if(COMBINE_CACHE.containsKey(key)){
	return COMBINE_CACHE.get(key);
	
}
 List<List<Integer>> result = new ArrayList<>();
 COMBINE_CACHE.put(key,result);

List<Integer> t = new ArrayList<>();
result.add(t);
// 求3个数的组合个数
int o1[] = new int[n];  用于输出的 
 用于输出的 
int u1[] = new int[n];  用来标记当前元素所使用的次数 
combine(u1, o1, n, m, 0, result);
return result;
}

public static void combine(int u[], int o[], int n, int m, int p,
	List<List<Integer>> data) {
// System.out.println(s+":"+n+":"+m);
int i;
if (p == m)  达到所选则的数目 
{
	for (i = 0; i < m; i++) {
		//System.out.print(o[i]+"-");
		data.get(data.size() - 1).add(o[i]);
	}//System.out.println();
	data.add(new ArrayList<Integer>());
	// System.out.println();
} else {
	for (i = 0; i < n; i++) {
		if (u[i] != m)  如当前元素为达到使用次数即m次 
		{
			u[i] += 1;
			o[p] = i;
			combine(u, o, n, m, p + 1, data);
			u[i] -= 1;
		}
	}
}
}

public static void removePai(List<Integer> memberShouPai,
	int[][] shuZuallPai, Integer temp) {
memberShouPai.remove(temp);
shuZuallPai[temp / 10 - 1][temp % 10] = shuZuallPai[temp / 10 - 1][temp % 10] - 1;
shuZuallPai[temp / 10 - 1][0] = shuZuallPai[temp / 10 - 1][0] - 1;

}

public static void addPai(List<Integer> memberShouPai, int[][] shuZuallPai,
	Integer temp) {
memberShouPai.add(temp);
shuZuallPai[temp / 10 - 1][temp % 10] = shuZuallPai[temp / 10 - 1][temp % 10] + 1;
shuZuallPai[temp / 10 - 1][0] = shuZuallPai[temp / 10 - 1][0] + 1;

}



*//**
	 * 重复组合
	 * 
	 * @param n
	 * @param k
	 * @return
	 *//*
		public static List<List<Integer>> dpcombinational(int n, int k)
		{
		String key = n+"_"+k;
		if(COMBINE_CACHE.containsKey(key)){
			return COMBINE_CACHE.get(key);
		}
		 int[] a = new int[n*k];
		 List<List<Integer>> result = new ArrayList<>();
		 COMBINE_CACHE.put(key,result);
		 
		 
		 int ret = 0;
		
		 //if selection count is 1, set the initial number to 1..n
		 for(int i=0; i<n; i++)
		     a[i*k] = i+1;
		
		 //if total number of the set is 1, set the initial number to 1
		 for(int i=1; i<k; i++)
		     a[i] = 1;
		
		 //recursion solution
		 for(int i=1; i<n; i++)
		     for(int j=1; j<k; j++)
		         a[i*k + j] = a[i*k + (j-1)] + a[(i-1)*k + j];
		
		 ret = a[n*k-1];
		
		
		 return result;
		}
		
		//一条龙
		public static boolean isOneGragon(int[][] allPai,List<GPaiQiang.Builder> showPai){	
		int[][] newAllPai=allPai.clone();
		for (Builder builder : showPai) {
			GPaiInfo pai = builder.getPai();
			List<Integer> paiList = pai.getPaiList();
			for(Integer i: paiList){
				int x= i/10-1;
				int y= i%10;
				newAllPai[x][0]+=1;
				newAllPai[x][y]+=1;
			}
		}
		for (int x = 0; x < newAllPai.length; x++) {  
		     if(newAllPai[x][0]>=9){
		     	for (int y = 0; y < newAllPai[x].length; y++) {
		     		if(newAllPai[x][y]==0){
		     			break;
		     		}
		     		if(y==9){
		     			return true;
		     		}
		     	}             	
		     }
		 }  
		return false;
		
		}
		
		}
		
		*/