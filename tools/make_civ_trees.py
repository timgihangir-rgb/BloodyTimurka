# -*- coding: utf-8 -*-
"""Собирает деревья фокусов стран, которые появляются при гражданской войне.

Четыре страны из деревьев fra / pol / ger_n / rus_i возникают как отделившиеся
государства, и игрок после раскола продолжает играть именно за них (эффект
CivilWar с TakeControl). Поэтому у каждой должно быть своё дерево, иначе
партия упрётся в пустой фокусный экран.

Здесь только содержание: название, родители, ходы, эффекты и подписи. Формат
файлов тот же, что у деревьев написанных вручную (data/focus/<tag>/<tag>_focus.json
и data/lang/focus_<tag>_ru.properties), поэтому править можно и их.

    python3 tools/make_civ_trees.py
"""
import io
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
FOCUS_ROOT = os.path.join(ROOT, 'data', 'focus')
LANG_ROOT = os.path.join(ROOT, 'data', 'lang')

# Базовый год: расколы в деревьях fra/pol/ger_n/rus_i стоят на 1940-1950-х,
# поэтому и ветки новых стран привязаны примерно к этому времени.
BASE_YEAR = 1950

# key, родители, ходы, AI-вес, эффекты, RU-название, EN-название,
# RU-описание, EN-описание
# Эффект: ('Stat', имя, значение) | ('Money', значение) | ('Happiness', значение)
#          ('Modifier', имя, значение) | ('Annexation', жертва, сколько, доля)
TREES = {}

# ===========================================================================
# fra_b - Бонапартистская империя. Отделилась с Парижа и Лиона: столица
# империи и её второй город, между которыми и тянулась её история.
# ===========================================================================
TREES['fra_b'] = ('BE2_F_FRAB_', 'eng', [
    ('army', [], 25, 60,
     [('Stat', 'MilitaryPoints', 700), ('Stat', 'ManPower', 60000), ('Happiness', 5)],
     'Великая армия', 'The Grande Armée',
     'Империя начинается с армии: под знаменем из Парижа собирают тех, кто готов '
     'идти на восток, и тех, кто не готов, но молчит.',
     'An empire begins with an army: under the banner from Paris they gather '
     'those ready to march east, and those not ready but silent.'),
    ('dragoons', ['army'], 30, 55,
     [('Stat', 'MilitaryPoints', 600), ('Money', -2000), ('Modifier', 'AttackBonus', 0.2)],
     'Императорские драгуны', 'Imperial Dragoons',
     'Солдаты получают имена, мундиры и генерал\'ские пенсии. Дисциплина стоит '
     'дороже, но армия перестаёт разбегаться сама.',
     'Soldiers get names, uniforms and pensions. Discipline is expensive, but '
     'the army stops running away on its own.'),
    ('fortress', ['dragoons'], 45, 50,
     [('Stat', 'Stability', 0.1), ('Money', -3000), ('Modifier', 'DefenseBonus', 0.2)],
     'Стоянки и крепости', 'Barracks and Fortresses',
     'Каждый город второй столицы становится складом, а каждый мост на дороге '
     'из Парижа - воротами. Страна, которую нельзя быстро пройти, живёт '
     'дольше.',
     'Every city of the second capital becomes a warehouse and every bridge on '
     'the road from Paris a gate. A country that cannot be crossed quickly '
     'lives longer.'),
    ('cavalry', ['fortress'], 40, 45,
     [('Stat', 'MilitaryPoints', 1500), ('Modifier', 'MovementPoints', 0.2)],
     'Тяжёлая кавалерия', 'Heavy Cavalry',
     'Конница решает то, чего не решают числа: расстояние и настроение '
     'приграничных гарнизонов.',
     'Cavalry settles what numbers cannot: distance and the mood of the border '
     'garrisons.'),
    ('empirewar', ['cavalry'], 70, 35,
     [('Stat', 'MilitaryPoints', 2500), ('Stat', 'ManPower', 200000),
      ('Happiness', -10)],
     'Империальная война', 'Imperial War',
     'Империя объявляет войну не ради границы, а ради того, чтобы граница была '
     'везде. Дорого, долго и по-старому.',
     'The empire declares war not for a border but to have borders everywhere. '
     'Expensive, long, and old-fashioned.'),

    ('plebiscite', [], 25, 60,
     [('Stat', 'Stability', 0.15), ('Stat', 'DiplomacyPoints', 500), ('Happiness', 8)],
     'Плебисцит', 'Plebiscite',
     'Вопрос задают прямо и вслух, и страна отвечает вслух же. Ответ '
     'заранее известен, но он был заранее объявлен, и это главное.',
     'The question is asked plainly and aloud, and the country answers aloud. '
     'The answer was known in advance, but it was announced in advance, and that '
     'is the main thing.'),
    ('senate', ['plebiscite'], 30, 55,
     [('Stat', 'Stability', 0.12), ('Stat', 'TaxationLevel', 1), ('Money', 3000)],
     'Сенат', 'The Senate',
     'Собрание, где все сидят, все молчат и все выходят с одинаковым лицом. '
     'Управлять таким собранием проще, чем страной.',
     'An assembly where everyone sits, everyone is silent, and everyone leaves '
     'with the same face. Governing such an assembly is easier than governing a '
     'country.'),
    ('ministry', ['senate'], 40, 50,
     [('Stat', 'Stability', 0.1), ('Money', 2500), ('Modifier', 'Administration', -0.1)],
     'Министерство имперских дел', 'Ministry of Imperial Affairs',
     'Каждая область получает своего чиновника из Парижа и отчёт, который '
     'идёт обратно в Париж. Управление дорожает и перестаёт спорить само.',
     'Every region gets an official from Paris and a report that goes back to '
     'Paris. Rule gets more expensive and stops arguing with itself.'),
    ('order', ['ministry'], 40, 45,
     [('Stat', 'Stability', 0.14), ('Happiness', -6), ('Stat', 'MilitaryPoints', 500)],
     'Полиция империи', 'The Imperial Police',
     'Порядок, который держится на жандармах, а не на согласии. Города '
     'становятся тише, и в тишине слышно, где именно недовольны.',
     'Order kept by gendarmes rather than consent. Cities get quieter, and in '
     'the quiet one hears exactly where the discontent is.'),
    ('legitimacy', ['order'], 60, 30,
     [('Stat', 'Stability', 0.15), ('Happiness', 10), ('Modifier', 'IncomeTaxation', 0.1)],
     'Династия', 'The Dynasty',
     'Трон становится не местом, а обещанием: наследник есть, и это снимает '
     'вопрос о том, что будет после.',
     'The throne becomes not a place but a promise: there is an heir, and that '
     'settles the question of what comes after.'),

    ('bank', [], 30, 55,
     [('Money', 4000), ('Modifier', 'IncomeTaxation', 0.1), ('Stat', 'TaxationLevel', 1)],
     'Имперский банк', 'The Imperial Bank',
     'Деньги начинают ходить по стране, а не только по столам министров. '
     'Империя берёт в долг у будущего и называет это инвестицией.',
     'Money starts moving through the country, not only across ministers’ desks. '
     'The empire borrows from the future and calls it investment.'),
    ('rail', ['bank'], 40, 55,
     [('Money', 3500), ('Modifier', 'IncomeProduction', 0.2)],
     'Железные дороги', 'Railways',
     'Линия Париж - Лион превращается в линию Париж - везде. Страна с '
     'рельсами приезжает раньше, чем приходят вести о войне.',
     'The Paris-Lyon line becomes the Paris-everywhere line. A country with '
     'rails arrives before the news of war does.'),
    ('industry', ['rail'], 45, 50,
     [('Money', 4000), ('Modifier', 'IncomeProduction', 0.2), ('Stat', 'Stability', 0.05)],
     'Имперская промышленность', 'Imperial Industry',
     'Заводы строят то, что нужно армии, а то, что можно продать, - в '
     'перерыв между войнами.',
     'Plants build what the army needs; what can be sold is built in the pause '
     'between wars.'),
    ('canal', ['industry'], 45, 45,
     [('Money', 2500), ('Modifier', 'IncomeProduction', 0.15), ('Stat', 'Food', 2000)],
     'Каналы и порты', 'Canals and Ports',
     'Вода вместо дороги: дешевле, медленнее и не боится блокады.',
     'Water instead of roads: cheaper, slower, and not afraid of a blockade.'),
    ('colony', ['canal'], 65, 30,
     [('Money', 5000), ('Stat', 'Food', 2000), ('Modifier', 'EconomyGrowth', 0.1),
      ('Happiness', -4)],
     'Заморские владения', 'Overseas Possessions',
     'Страна снова покупает земли за морем, не веря в метрополию, но не '
     'проверяя её. Товары идут морем, а письма - тоже.',
     'The country buys lands across the sea again, not trusting the metropole '
     'but not checking it either. Goods travel by sea, and so do letters.'),

    ('legion', [], 30, 50,
     [('Stat', 'Stability', 0.1), ('Happiness', 8), ('Modifier', 'Research', 0.1)],
     'Почётный легион', 'The Legion of Honour',
     'Награда за службу, которая ничего не стоит правительству и стоит '
     'слишком много награждённым.',
     'A reward for service that costs the government nothing and costs the '
     'decorated far too much.'),
    ('education', ['legion'], 45, 50,
     [('Modifier', 'Research', 0.15), ('Stat', 'TaxationLevel', 1), ('Money', 2000),
      ('Stat', 'Stability', 0.05)],
     'Императорские школы', 'Imperial Schools',
     'Учебник пишут так, чтобы через двадцать лет никто не помнил, кто был '
     'настоящим императором.',
     'The textbooks are written so that in twenty years nobody remembers who the '
     'real emperor was.'),
    ('science', ['education'], 50, 45,
     [('Modifier', 'Research', 0.2), ('Money', 3000), ('Stat', 'Stability', 0.06)],
     'Академия наук', 'Academy of Sciences',
     'Учёные получают то, чего не получали их коллеги при прошлой власти: '
     'отдельные здания и тишину.',
     'Scholars get what their colleagues did not get under the last '
     'government: separate buildings and quiet.'),
    ('art', ['science'], 40, 40,
     [('Happiness', 12), ('Stat', 'Stability', 0.06), ('Money', 2000)],
     'Императорские залы', 'Imperial Halls',
     'Музеи и залы, где картина должника висит выше карты маршала. Вкус '
     'империи - это тоже политика.',
     'Museums and halls where a debtor’s painting hangs above a marshal’s map. '
     'The empire’s taste is politics too.'),
    ('memory', ['art'], 60, 30,
     [('Stat', 'Stability', 0.12), ('Happiness', -8), ('Modifier', 'IncomeTaxation', 0.1)],
     'Память империи', 'Memory of the Empire',
     'Страна решает, что помнить, и выселяет из памяти всё остальное. '
     'Границы после этого проще защищать: они проходят по памяти.',
     'The country decides what to remember and moves everything else out of '
     'memory. Borders are easier to defend afterwards: they run through memory.'),

    ('march', [], 25, 55,
     [('Stat', 'MilitaryPoints', 600), ('Stat', 'ManPower', 40000), ('Happiness', -4)],
     'Выход из Парижа', 'Out of Paris',
     'Страна, запертая в двух городах, начинает смотреть на карту. Провинции '
     'соседей становятся вопросом, на который рано или поздно отвечают.',
     'A country shut in two cities starts looking at a map. The neighbours’ '
     'provinces become a question that is answered sooner or later.'),
    ('rhone', ['march'], 35, 50,
     [('Annexation', '*', 1, 0.0), ('Stat', 'MilitaryPoints', 800)],
     'Долина Роны', 'The Rhône Valley',
     'Первая земля за Лионом: виноградники, мосты и города, которые до этого '
     'принадлежали другой империи.',
     'The first land past Lyon: vineyards, bridges and towns that until now '
     'belonged to another empire.'),
    ('atlantic', ['rhone'], 40, 50,
     [('Annexation', '*', 2, 0.0), ('Stat', 'MilitaryPoints', 1000), ('Stat', 'Food', 2000)],
     'Атлантическое побережье', 'The Atlantic Coast',
     'Бордо и река, на которой держалась вся торговля юга. Гавань становится '
     'второй столицей.',
     'Bordeaux and the river that carried all southern trade. The harbour '
     'becomes a second capital.'),
    ('mediterranee', ['atlantic'], 45, 45,
     [('Annexation', '*', 2, 0.0), ('Stat', 'MilitaryPoints', 1200),
      ('Modifier', 'IncomeProduction', 0.1)],
     'Средиземноморье', 'The Mediterranean',
     'Порт на юге даёт флот и экспорт, а вместе с ними - прямой путь в '
     'Африку и обратно.',
     'A southern port gives a fleet and exports, and with them a direct road to '
     'Africa and back.'),
    ('hexagone', ['mediterranee'], 75, 30,
     [('Annexation', '*', 2, 0.0), ('Stat', 'MilitaryPoints', 2000), ('Happiness', -8),
      ('Stat', 'Stability', 0.08)],
     'Шестиугольник', 'The Hexagon',
     'Границы империи совпадают с границами той страны, которая её породила. '
     'Круг замкнулся, и это стоит дороже, чем казалось.',
     'The empire’s borders match those of the country that produced it. The '
     'circle is closed, and it cost more than it seemed.'),
])


# ===========================================================================
# ger_q - Германская республика. Отделилась от монархии её восточная,
# промышленная половина: рурский уголь, сталь и несогласные профсоюзы.
# ===========================================================================
TREES['ger_q'] = ('BE2_F_GERQ_', 'ger_n', [
    ('versammlung', [], 25, 60,
     [('Stat', 'Stability', 0.12), ('Happiness', 10), ('Stat', 'TaxationLevel', 1)],
     'Национальное собрание', 'National Assembly',
     'Страна, у которой отняли корону, пробует говорить сама. Собрание '
     'проходит плохо, но республика начинается именно с этого.',
     'A country whose crown has been taken away tries to speak for itself. '
     'The assembly goes badly, but a republic begins exactly here.'),
    ('verfassung', ['versammlung'], 40, 55,
     [('Stat', 'Stability', 0.15), ('Stat', 'TaxationLevel', 1), ('Happiness', 5)],
     'Конституция', 'The Constitution',
     'Писаные правила, спорные статьи и один вопрос без ответа: кто '
     'командование в кризисе. Республика держится на этой бумаге.',
     'Written rules, disputed articles and one unanswered question: who '
     'commands in a crisis. The republic holds on this paper.'),
    ('wahlrecht', ['verfassung'], 35, 50,
     [('Stat', 'Stability', 0.1), ('Happiness', 12), ('Stat', 'DiplomacyPoints', 400)],
     'Избирательное право', 'The Right to Vote',
     'Женщины получают голос раньше, чем большинство мужчин голосуют за '
     'партию. Страна делает шаг, который потом не отменяет.',
     'Women get the vote before most men vote for a party. The country takes a '
     'step it will not undo.'),
    ('bundesrat', ['wahlrecht'], 40, 45,
     [('Stat', 'Stability', 0.12), ('Modifier', 'Administration', -0.1),
      ('Money', 2000)],
     'Союзный совет', 'The Federal Council',
     'Земли получают право говорить в один голос. Центра слабеет, зато страна '
     'не рвётся на части при первом же кризисе.',
     'The states get the right to speak with one voice. The centre weakens, but '
     'the country does not tear apart at the first crisis.'),
    ('stabilitaet', ['bundesrat'], 55, 35,
     [('Stat', 'Stability', 0.15), ('Happiness', -6), ('Modifier', 'IncomeTaxation', 0.1)],
     'Стабильность', 'Stability',
     'Страна закупает спокойствие: индексация зарплат, субсидии угольной '
     'промышленности и тихие соглашения вместо забастовок.',
     'The country buys calm: wage indexation, coal subsidies and quiet deals '
     'instead of strikes.'),

    ('goldmark', [], 30, 55,
     [('Modifier', 'IncomeTaxation', 0.15), ('Stat', 'Stability', 0.08), ('Money', 3000)],
     'Золотой марк', 'The Gold Mark',
     'Ремонт денег начинают с самой простой вещи: перестают печатать лишнее. '
     'Рынок оживает, а с ним - сомнения о происхождении этих денег.',
     'Money repair starts with the simplest thing: stop printing extra. The '
     'market revives, and with it doubts about where this money came from.'),
    ('banken', ['goldmark'], 35, 50,
     [('Money', 4000), ('Modifier', 'IncomeProduction', 0.15), ('Stat', 'Stability', 0.05)],
     'Государственные банки', 'State Banks',
     'Банки переходят под контроль государства и перестают закрываться по '
     'первому слуху. Страна теряет часть независимости и часть паники.',
     'The banks come under state control and stop closing at the first rumour. '
     'The country loses some independence and some panic.'),
    ('ruhr', ['banken'], 45, 50,
     [('Money', 4500), ('Modifier', 'IncomeProduction', 0.2), ('Stat', 'Food', 2000)],
     'Рурский уголь', 'Ruhr Coal',
     'Гибель рудника стоит республике дороже, чем дешёвый уголь того стоил бы. '
     'Профсоюз требует, государство платит, шахта работает.',
     'The death of a mine costs the republic more than cheap coal would have. '
     'The union demands, the state pays, the mine works.'),
    ('industrie', ['ruhr'], 50, 45,
     [('Money', 4000), ('Modifier', 'IncomeProduction', 0.2), ('Stat', 'Stability', 0.06)],
     'Стальная республика', 'The Steel Republic',
     'Страна делает ставку на сталь и проигрывает, когда сталь не нужна. '
     'Но она умеет делать её быстрее соседей.',
     'The country bets on steel and loses when steel is not needed. But it can '
     'make it faster than its neighbours.'),
    ('rationalisierung', ['industrie'], 60, 30,
     [('Money', 3000), ('Happiness', -14), ('Stat', 'Stability', 0.1),
      ('Modifier', 'IncomeProduction', 0.15)],
     'Рационализация', 'Rationalisation',
     'Закрывают лишнее, увольняют лишних и называют это эффективностью. '
     'Показатели растут, семьи переезжают в другие города.',
     'Unnecessary things are closed, unnecessary people are dismissed, and it '
     'is called efficiency. The figures grow, the families move to other cities.'),

    ('reichswehr', [], 30, 55,
     [('Stat', 'MilitaryPoints', 800), ('Stat', 'Stability', 0.08),
      ('Modifier', 'MilitaryUpkeep', -0.1)],
     'Рейхсвер', 'The Reichswehr',
     'Армия, которой запрещено учиться воевать и разрешено учиться держать '
     'строй. Этого хватает, чтобы не быть побеждённым сразу.',
     'An army forbidden from learning to fight and allowed to learn to hold '
     'line. That is enough not to be defeated at once.'),
    ('vorwarts', ['reichswehr'], 35, 50,
     [('Stat', 'MilitaryPoints', 500), ('Stat', 'Stability', 0.06), ('Happiness', 6)],
     '«Вперёд, ребята»', '"Forward, men"',
     'Полковник-идеолог приводит войска с собой, и они идут туда, куда он '
     'скажет. Войска становятся сильнее и менее управляемыми.',
     'A colonel-ideologue leads the troops personally, and they go where he '
     'says. The army grows stronger and less controllable.'),
    ('wehrreform', ['vorwarts'], 40, 45,
     [('Stat', 'MilitaryPoints', 1200), ('Money', -2500), ('Modifier', 'AttackBonus', 0.2)],
     'Реформа армии', 'Army Reform',
     'Новая техника вместо старой: она дороже, медленнее и решает, что будет '
     'через пять лет.',
     'New equipment instead of old: costlier, slower, and it decides what '
     'happens in five years.'),
    ('luftwaffe', ['wehrreform'], 50, 40,
     [('Stat', 'MilitaryPoints', 1500), ('Money', -3000), ('Modifier', 'Research', 0.1)],
     'Воздушные силы', 'The Air Force',
     'Республика, которой запрещали иметь авиацию, строит её тайком и '
     'называет это гражданской авиацией.',
     'A republic forbidden to have an air force builds it in secret and calls '
     'it civil aviation.'),
    ('kampfbund', ['luftwaffe'], 70, 30,
     [('Stat', 'MilitaryPoints', 2500), ('Stat', 'ManPower', 200000),
      ('Happiness', -6)],
     'Союз борьбы', 'The Combat League',
     'Добровольцы, для которых республика - это гражданская война, которую '
     'они не хотели. Страна получает армию, которая воюет с гражданами '
     'охотнее, чем с врагами.',
     'Volunteers for whom the republic is the civil war they did not want. The '
     'country gets an army that fights citizens more willingly than enemies.'),

    ('spd', [], 30, 50,
     [('Stat', 'Stability', 0.08), ('Happiness', 8), ('Modifier', 'IncomeTaxation', 0.05)],
     'Социал-демократия', 'Social Democracy',
     'Партия, которая обещает всё сразу и поэтому выигрывает всё сразу. '
     'Правление становится лучше, а ожидания - выше.',
     'A party that promises everything at once and therefore wins everything at '
     'once. Government gets better, expectations get higher.'),
    ('gewerkschaft', ['spd'], 40, 50,
     [('Stat', 'Stability', 0.1), ('Happiness', 6), ('Stat', 'TaxationLevel', 1)],
     'Профсоюзы', 'The Trade Unions',
     'Рабочие перестают быть наёмной силой и становятся стороной договора. '
     'Зарплаты растут вместе с ценами, а страна - вместе с долгом.',
     'Workers stop being hired help and become a party to the contract. Wages '
     'rise with prices, and the country rises with debt.'),
    ('arbeitsdienst', ['gewerkschaft'], 40, 45,
     [('Modifier', 'EconomyGrowth', 0.15), ('Stat', 'Stability', 0.08),
      ('Stat', 'Food', 2000)],
     'Трудовая служба', 'Labour Service',
     'Безработных отправляют строить дороги, и они действительно строят. '
     'Проблема занятости остаётся, но становится тише.',
     'The unemployed are sent to build roads, and they really build. The '
     'employment problem stays but gets quieter.'),
    ('koalition', ['arbeitsdienst'], 45, 40,
     [('Stat', 'Stability', 0.12), ('Happiness', 6), ('Stat', 'DiplomacyPoints', 300)],
     'Коалиция', 'The Coalition',
     'Страна решает не выбирать, а договариваться. Правление работает '
     'медленнее и держится дольше.',
     'The country decides not to vote but to negotiate. Government works more '
     'slowly and lasts longer.'),
    ('restrukturierung', ['koalition'], 60, 30,
     [('Stat', 'Stability', 0.15), ('Happiness', -10), ('Modifier', 'EconomyGrowth', 0.1)],
     'Реструктуризация', 'Restructuring',
     'Страна переживает кризис, снимая с себя то, что не приносит дохода. '
     'Республика выживает и перестаёт быть прежней.',
     'The country survives a crisis by shedding what brings no income. The '
     'republic survives and stops being the old one.'),

    ('nordsee', [], 25, 55,
     [('Stat', 'Stability', 0.1), ('Happiness', 4), ('Stat', 'Food', 1000)],
     'Северное море', 'The North Sea',
     'Порты вместо суши: страна сдаёт то, что нельзя удержать, и берёт то, '
     'что нельзя обойти.',
     'Harbours instead of land: the country gives up what cannot be held and '
     'takes what cannot be bypassed.'),
    ('hamburg', ['nordsee'], 35, 50,
     [('Annexation', '*', 1, 0.0), ('Money', 3000), ('Stat', 'MilitaryPoints', 600)],
     'Гамбург', 'Hamburg',
     'Вольный город входит в состав государства не силой, а договором. '
     'Первый случай, когда республика что-то получила, а не отняла.',
     'The free city joins the state not by force but by treaty. The first case '
     'of the republic receiving something rather than taking it.'),
    ('rheinkolonien', ['hamburg'], 40, 50,
     [('Annexation', '*', 2, 0.0), ('Stat', 'MilitaryPoints', 1000), ('Money', 2500)],
     'Рейнские земли', 'The Rhineland',
     'Провинции на западе, которые республика получила вместе с её '
     'восточными. Страна снова спорит с соседями о границе.',
     'The western provinces the republic received along with its eastern ones. '
     'The country argues with its neighbours about a border again.'),
    ('sudwesten', ['rheinkolonien'], 45, 45,
     [('Annexation', '*', 1, 0.0), ('Stat', 'MilitaryPoints', 900),
      ('Modifier', 'IncomeProduction', 0.1)],
     'Юго-запад', 'The Southwest',
     'Южные земли присоединяются, и республика впервые видит море там, где '
     'раньше была граница.',
     'The southern lands join, and the republic sees the sea for the first time '
     'where a border used to be.'),
    ('europahauptstadt', ['sudwesten'], 80, 30,
     [('Annexation', '*', 2, 0.0), ('Stat', 'MilitaryPoints', 1800),
      ('Stat', 'Stability', 0.1), ('Happiness', -8)],
     'Столица Европы', 'Capital of Europe',
     'Страна, выросшая на угле и долгах, заявляет, что её столица - там, откуда '
     'можно давить на всех. Это стоит дороже всего остального вместе взятого.',
     'A country grown on coal and debt claims that its capital is wherever it '
     'can press everyone from. This costs more than everything else combined.'),
])


# ===========================================================================
# pol_c - Польская Народная Республика. Отделилась с Варшавы, Катовиц и
# Гданьска: столица, силезский уголь и балтийский порт.
# ===========================================================================
TREES['pol_c'] = ('BE2_F_POLC_', 'pol', [
    ('partia', [], 25, 60,
     [('Stat', 'Stability', 0.12), ('Happiness', 8), ('Stat', 'TaxationLevel', 1)],
     'Партия', 'The Party',
     'Страна начинается не с государства, а с партии, которая уверена, что '
     'государство ей подчиняется. Обычно выходит наоборот.',
     'The country begins not with a state but with a party that assumes the '
     'state obeys it. Usually it is the other way round.'),
    ('kongres', ['partia'], 35, 55,
     [('Stat', 'Stability', 0.12), ('Stat', 'DiplomacyPoints', 400), ('Happiness', 6)],
     'Партийный съезд', 'The Party Congress',
     'Съезд решает всё, включая то, что решать не должен. Страна получает '
     'быстрое управление и отсутствие апелляции.',
     'The congress decides everything, including what it should not decide. '
     'The country gets fast rule and no appeal.'),
    ('sowiety', ['kongres'], 40, 50,
     [('Stat', 'Stability', 0.1), ('Happiness', 5), ('Modifier', 'Administration', 0.1)],
     'Советы на местах', 'Local Soviets',
     'Власть переносится туда, где её видно. Страна решает быстрее и ошибается '
     'ближе к земле, а значит - дешевле.',
     'Power moves where it can be seen. The country decides faster and errs '
     'closer to the ground, which means cheaper.'),
    ('planowanie', ['sowiety'], 45, 45,
     [('Modifier', 'EconomyGrowth', 0.2), ('Stat', 'Food', 2000), ('Money', 2500),
      ('Stat', 'Stability', 0.06)],
     'Планирование', 'Planning',
     'Страна решает, сколько угля будет добыто в 1953 году, и под это выдаёт '
     'всё остальное. План спасает страну и заканчивается вместе с ней.',
     'The country decides how much coal will be mined in 1953 and gives '
     'everything else to match. The plan saves the country and ends with it.'),
    ('pieciolatka', ['planowanie'], 60, 35,
     [('Money', 4000), ('Modifier', 'IncomeProduction', 0.2), ('Happiness', -8),
      ('Stat', 'Stability', 0.1)],
     'Пятилетка', 'The Five-Year Plan',
     'План, у которого есть срок и цифры. Страна строит много и не то, и '
     'через пять лет либо берёт план выше, либо берёт чужой.',
     'A plan with a deadline and numbers. The country builds a lot of the wrong '
     'things, and in five years either raises the plan or takes someone else’s.'),

    ('milicja', [], 25, 60,
     [('Stat', 'MilitaryPoints', 700), ('Stat', 'ManPower', 50000), ('Happiness', 5)],
     'Рабочая милиция', 'The Workers’ Militia',
     'Шахтёры и портовые рабочие получают винтовку вместе с зарплатой. '
     'Они воюют лучше армии, потому что воюют за свой дом.',
     'Miners and dockers get a rifle together with a wage. They fight better '
     'than an army because they fight for their own home.'),
    ('broni', ['milicja'], 30, 55,
     [('Stat', 'MilitaryPoints', 600), ('Money', -2000), ('Modifier', 'AttackBonus', 0.15)],
     'Оружейные заводы', 'Arms Plants',
     'Страна, у которой есть уголь и железо, делает оружие вместо того, чтобы '
     'покупать. Заводы стоят у Силезии, и война приходит к ним сама.',
     'A country with coal and iron makes weapons instead of buying them. The '
     'plants stand near Silesia, and war comes to them by itself.'),
    ('frontovoy', ['broni'], 40, 50,
     [('Stat', 'MilitaryPoints', 1200), ('Stat', 'ManPower', 100000),
      ('Modifier', 'MovementPoints', 0.15)],
     'Народная армия', 'The People’s Army',
     'У армии есть национальная идея, и это дешевле любого генерала. Она '
     'появляется у шахт, где люди уже умеют держать строй.',
     'The army has a national idea, which is cheaper than any general. It '
     'appears at the mines, where people already know how to stand in line.'),
    ('lotnictwo', ['frontovoy'], 45, 45,
     [('Stat', 'MilitaryPoints', 1500), ('Money', -3000), ('Modifier', 'Research', 0.1)],
     'Авиация', 'Aviation',
     'Страна строит самолёты, потому что её граница - это воздух, а не '
     'камень. Пилотов набирают из тех, кто чинит тракторы.',
     'The country builds aircraft because its border is air, not stone. Pilots '
     'are recruited from those who repair tractors.'),
    ('wojsko', ['lotnictwo'], 70, 30,
     [('Stat', 'MilitaryPoints', 2500), ('Stat', 'ManPower', 250000),
      ('Happiness', -6)],
     'Страна на военном положении', 'The Country on War Footing',
     'Вся экономика становится военной. Страна выигрывает быстро и всё реже.',
     'The whole economy becomes military. The country wins quickly and ever '
     'more rarely.'),

    ('nowa_huta', [], 30, 55,
     [('Money', 4000), ('Modifier', 'IncomeProduction', 0.2), ('Stat', 'Food', 1500)],
     'Новая Гута', 'Nowa Huta',
     'Новый металлургический завод строят рядом с углём, а не рядом с '
     'рынком. Так в стране появляется город, которого ещё нет на картах '
     'соседей.',
     'The new steelworks is built next to the coal, not next to the market. So '
     'a city appears that is not yet on the neighbours’ maps.'),
    ('siec', ['nowa_huta'], 40, 55,
     [('Money', 3500), ('Modifier', 'IncomeProduction', 0.2), ('Stat', 'Food', 2000)],
     'Железные дороги сети', 'The Network of Rails',
     'Страна связывает уголь, руду и порт в одну схему, и в этой схеме всё '
     'зависит от того, будет ли зима мягкой.',
     'The country links coal, ore and port into one scheme, and in that scheme '
     'everything depends on whether the winter is mild.'),
    ('przemysl', ['siec'], 45, 50,
     [('Money', 4000), ('Modifier', 'IncomeProduction', 0.2), ('Stat', 'Stability', 0.05)],
     'Тяжёлая промышленность', 'Heavy Industry',
     'Страна делает то, что неудобно везти, но выгодно продавать. Рынок '
     'соседей узнаёт её продукцию раньше, чем её флаг.',
     'The country makes what is awkward to ship but good to sell. Neighbours’ '
     'markets learn its goods before they learn its flag.'),
    ('eksport', ['przemysl'], 45, 45,
     [('Money', 3500), ('Modifier', 'IncomeTaxation', 0.15), ('Stat', 'Stability', 0.06)],
     'Экспорт', 'Export',
     'Валюта приходит извне, и страна привыкает к мысли, что её '
     'благополучие зависит от чужой покупательной способности.',
     'Currency arrives from outside, and the country gets used to the thought '
     'that its wealth depends on someone else’s ability to buy.'),
    ('uprzemyslenie', ['eksport'], 70, 30,
     [('Money', 4500), ('Modifier', 'IncomeProduction', 0.25), ('Happiness', -10),
      ('Stat', 'Stability', 0.1)],
     'Упразднение мелкой промышленности', 'Abolishing Small Industry',
     'Мелкие мастерские закрывают, а их людей переводят в большие цеха. '
     'Страна производит больше и ненавидит себя чуть больше.',
     'Small workshops are closed and their people moved to large shops. The '
     'country produces more and dislikes itself slightly more.'),

    ('szkola', [], 30, 50,
     [('Modifier', 'Research', 0.1), ('Stat', 'TaxationLevel', 1), ('Money', 1500),
      ('Stat', 'Stability', 0.05)],
     'Народное просвещение', 'Public Education',
     'Школа учит читать, писать и считать - и это делает страну грамотной '
     'раньше, чем богатой.',
     'The school teaches reading, writing and counting - and that makes the '
     'country literate before it makes it rich.'),
    ('nauka', ['szkola'], 45, 45,
     [('Modifier', 'Research', 0.2), ('Money', 2500), ('Stat', 'Stability', 0.06)],
     'Наука', 'Science',
     'Институты, где считают вслух и записывают результат. Страна начинает '
     'понимать, что она сама мешает своей промышленности.',
     'Institutes that count out loud and write down the result. The country '
     'begins to see that it is itself in the way of its own industry.'),
    ('radio', ['nauka'], 40, 45,
     [('Stat', 'Stability', 0.08), ('Happiness', -6), ('Modifier', 'Research', 0.05)],
     'Радио', 'Radio',
     'Страна говорит сразу со всеми и не слышит никого. Это решает половину '
     'внутренней политики и создаёт другую.',
     'The country talks to everyone at once and hears no one. This settles half '
     'the domestic policy and creates the other half.'),
    ('kultura', ['radio'], 40, 40,
     [('Happiness', 10), ('Stat', 'Stability', 0.05), ('Money', 2000)],
     'Культура', 'Culture',
     'Театры и газеты, которые можно критиковать заранее, до того как '
     'придут за критиком. Это дешёвый способ сохранить разговор.',
     'Theatres and papers that can be criticised in advance, before anyone '
     'comes for the critic. It is a cheap way to keep a conversation going.'),
    ('ideologia', ['kultura'], 65, 30,
     [('Stat', 'Stability', 0.15), ('Happiness', -10), ('Modifier', 'Research', 0.1)],
     'Государственная идеология', 'The State Ideology',
     'Страна решает, что́ думать, и записывает это в учебник. Учебник '
     'меняется, страна - нет.',
     'The country decides what to think and writes it into the textbook. The '
     'textbook changes, the country does not.'),

    ('wybrzeze', [], 25, 55,
     [('Stat', 'Stability', 0.1), ('Stat', 'Food', 1000), ('Happiness', 4)],
     'Выжнег', 'The Coastline',
     'Страна с портом и границей по воде. Всё, что было неясно в её начале, '
     'становится ясным у воды.',
     'A country with a port and a border on water. Whatever was unclear at its '
     'beginning becomes clear at the water.'),
    ('gdansk', ['wybrzeze'], 35, 50,
     [('Annexation', '*', 1, 0.0), ('Money', 3000), ('Stat', 'MilitaryPoints', 600)],
     'Гданьск', 'Gdańsk',
     'Свободный город сдаётся не добровольно: договор подписывают под '
     'угрозой блокады, и обе стороны делают вид, что это была встреча.',
     'The free city does not surrender voluntarily: the treaty is signed under '
     'threat of a blockade, and both sides act as if it had been a meeting.'),
    ('pomorze', ['gdansk'], 40, 50,
     [('Annexation', '*', 1, 0.0), ('Stat', 'MilitaryPoints', 900), ('Money', 2500)],
     'Поморье', 'Pomerania',
     'Земли вдоль воды присоединяются вместе с портами, и страна получает '
     'второй выход в море - уже свой, а не арендованный.',
     'The lands along the water join with their ports, and the country gets a '
     'second way to the sea - its own, not rented.'),
    ('wielkopolska', ['pomorze'], 45, 45,
     [('Annexation', '*', 1, 0.0), ('Stat', 'MilitaryPoints', 1000), ('Stat', 'Food', 2000)],
     'Великопольша', 'Wielkopolska',
     'Земли, которые меняли хозяев четыре раза за сто лет, меняют хозяев '
     'в пятый. На этот раз - навсегда, уверяет страна.',
     'Lands that changed hands four times in a century change hands a fifth. '
     'This time for good, the country assures itself.'),
    ('panstwo', ['wielkopolska'], 80, 30,
     [('Annexation', '*', 2, 0.0), ('Stat', 'MilitaryPoints', 2000),
      ('Stat', 'Stability', 0.1), ('Happiness', -8)],
     'Государство без границ', 'A State Without Borders',
     'Страна, начавшаяся в трёх городах, провозглашает себя тем, чем была '
     'раньше, - и с этого момента её существование приходится оправдывать.',
     'The country, which began in three cities, declares itself what it once '
     'was - and from that moment has to justify its own existence.'),
])


# ===========================================================================
# rus_q - Российская республика. Отделилась от федерации её запад: балтийские
# губернии и посты, от которых до столицы можно дойти пешком.
# ===========================================================================
TREES['rus_q'] = ('BE2_F_RUSQ_', 'rus_i', [
    ('uchreditelnoye', [], 25, 60,
     [('Stat', 'Stability', 0.12), ('Stat', 'TaxationLevel', 1), ('Happiness', 8)],
     'Учредительное собрание', 'The Constituent Assembly',
     'Страна, отколовшаяся от другой такой же, начинается с собрания, где '
     'спорят не о том, что делать, а о том, кто это будет делать.',
     'A country that split off from another like it begins with an assembly '
     'that argues not about what to do but about who will do it.'),
    ('konstitutsiya', ['uchreditelnoye'], 40, 55,
     [('Stat', 'Stability', 0.15), ('Stat', 'TaxationLevel', 1), ('Happiness', 5)],
     'Конституция', 'The Constitution',
     'Единственный документ, который в новой стране читают целиком. Он '
     'написан так, чтобы любая следующая власть могла им воспользоваться.',
     'The only document in the new country that is read in full. It is written '
     'so that any next government could make use of it.'),
    ('prezident', ['konstitutsiya'], 35, 50,
     [('Stat', 'Stability', 0.12), ('Stat', 'DiplomacyPoints', 400), ('Happiness', 6)],
     'Президент', 'The President',
     'Страна выбирает одного человека, которому доверяет всё, и получает '
     'либо страну, либо человека, который её заменит.',
     'The country picks one person to trust with everything, and gets either a '
     'country or a person who will replace it.'),
    ('parlament', ['prezident'], 40, 45,
     [('Stat', 'Stability', 0.1), ('Modifier', 'Administration', -0.1), ('Money', 2000)],
     'Парламент', 'The Parliament',
     'Власть делится, и страна медленнее принимает решения и быстрее их '
     'исполняет.',
     'Power is shared, and the country decides slower and executes faster.'),
    ('stabilizatsiya', ['parlament'], 55, 35,
     [('Stat', 'Stability', 0.15), ('Happiness', -6), ('Modifier', 'IncomeTaxation', 0.1)],
     'Стабилизация', 'Stabilisation',
     'Шаги, которые не нравятся никому: дороже уголь, выше налоги, тише '
     'улицы. Через год половина этих мер отменяется.',
     'Measures nobody likes: dearer coal, higher taxes, quieter streets. Within '
     'a year half of them are repealed.'),

    ('porty', [], 30, 55,
     [('Money', 3500), ('Modifier', 'IncomeProduction', 0.15), ('Stat', 'Food', 1500)],
     'Порты', 'The Ports',
     'Страна торгует через чужие причалы и платит за это процентом от '
     'каждого груза. Первое, что приходит на ум после флага, - это склад.',
     'The country trades through other people’s piers and pays a percentage on '
     'every cargo. The first thing that comes to mind after the flag is a '
     'warehouse.'),
    ('zheleznica', ['porty'], 40, 55,
     [('Money', 3000), ('Modifier', 'IncomeProduction', 0.2), ('Stat', 'Stability', 0.05)],
     'Железные дороги', 'The Railways',
     'Линия, соединяющая запад со столицей, решает всё: с ней страна '
     'торгует, без неё - голодает.',
     'The line joining the west to the capital solves everything: with it the '
     'country trades, without it it goes hungry.'),
    ('ugol', ['zheleznica'], 45, 50,
     [('Money', 4000), ('Modifier', 'IncomeProduction', 0.2), ('Stat', 'Food', 2000)],
     'Уголь и руда', 'Coal and Ore',
     'Страна роет то, что лежит у неё под ногами, и обнаруживает, что '
     'качеством оно хуже, чем казалось.',
     'The country digs what lies under its feet and finds it is of worse '
     'quality than it seemed.'),
    ('promyshlennost', ['ugol'], 50, 45,
     [('Money', 4000), ('Modifier', 'IncomeProduction', 0.2), ('Stat', 'Stability', 0.05)],
     'Промышленность', 'Industry',
     'Заводы строят быстро, потому что страна боится, что её догонит сосед. '
     'Это верная причина, но дорогая.',
     'Plants are built fast because the country fears being overtaken by a '
     'neighbour. A sound reason, and an expensive one.'),
    ('teplifikatsiya', ['promyshlennost'], 65, 30,
     [('Modifier', 'EconomyGrowth', 0.2), ('Money', 2500), ('Happiness', 8),
      ('Stat', 'Stability', 0.06)],
     'Тепло и жильё', 'Heat and Housing',
     'Страна решает, что главное - это горячая вода в каждой квартире. '
     'Соседи считают это слабостью, пока не выясняется, что её перевели.',
     'The country decides the main thing is hot water in every flat. Neighbours '
     'call it weakness until they learn it was exported.'),

    ('oponents', [], 30, 50,
     [('Stat', 'MilitaryPoints', 600), ('Stat', 'Stability', 0.1),
      ('Modifier', 'MilitaryUpkeep', -0.1)],
     'Добровольческая оборона', 'The Volunteer Defence',
     'Армия из тех, кто живёт в приграничных посёлках. Она знает местность '
     'лучше генерального штаба и обходится дороже.',
     'An army of the people who live in the border settlements. It knows the '
     'ground better than the general staff and costs more.'),
    ('prizyv', ['oponents'], 40, 50,
     [('Stat', 'ManPower', 150000), ('Stat', 'MilitaryPoints', 1200),
      ('Happiness', -10), ('Stat', 'Stability', 0.06)],
     'Призыв', 'Conscription',
     'Каждый мужчина страны получает срок службы. Армия становится большой, '
     'и страна получает армию, которую надо кормить в мирное время.',
     'Every man in the country gets a term of service. The army becomes large, '
     'and the country gets an army it must feed in peacetime.'),
    ('mekhanizatsiya', ['prizyv'], 45, 45,
     [('Stat', 'MilitaryPoints', 1800), ('Money', -3000), ('Modifier', 'AttackBonus', 0.2)],
     'Механизация', 'Mechanisation',
     'Лошади уходят из армии, но не сразу. Дорого, видимо, и надолго.',
     'Horses leave the army, but not immediately. It is expensive, visibly, and '
     'for a long time.'),
    ('aviatsiya', ['mekhanizatsiya'], 50, 40,
     [('Stat', 'MilitaryPoints', 1500), ('Money', -2500), ('Modifier', 'Research', 0.1)],
     'Авиация', 'Aviation',
     'Страна строит самолёты, чтобы не покупать их у того, кто может решить, '
     'продавать ли.',
     'The country builds aircraft rather than buy them from whoever can decide '
     'whether to sell.'),
    ('front', ['aviatsiya'], 70, 30,
     [('Stat', 'MilitaryPoints', 2500), ('Stat', 'ManPower', 250000),
      ('Happiness', -6)],
     'Страна на грани', 'The Country on the Edge',
     'Сосед требует уступить побережье, и отказ становится приговором: война '
     'начинается не решением, а давлением.',
     'A neighbour demands a strip of the coast, and refusal becomes the '
     'sentence: war begins not by decision but by pressure.'),

    ('gimnaziya', [], 30, 50,
     [('Modifier', 'Research', 0.1), ('Stat', 'TaxationLevel', 1), ('Money', 1500),
      ('Stat', 'Stability', 0.05)],
     'Гимназия', 'The Grammar School',
     'Школа для тех, кто умеет думать на бумаге. Страна получает '
     'несколько сотен человек, которые умеют считать быстрее остальных.',
     'A school for those who can think on paper. The country gets a few '
     'hundred people who can count faster than the rest.'),
    ('universitet', ['gimnaziya'], 45, 45,
     [('Modifier', 'Research', 0.2), ('Money', 2500), ('Stat', 'Stability', 0.06)],
     'Университет', 'The University',
     'Институт, который переживёт страну: его выпускники найдут работу и в '
     'соседнем государстве, если это государство возникнет раньше.',
     'An institute that will outlive the country: its graduates will find work '
     'in a neighbour if that neighbour appears first.'),
    ('pressa', ['universitet'], 40, 45,
     [('Stat', 'Stability', 0.08), ('Happiness', -8), ('Modifier', 'Research', 0.05)],
     'Пресса', 'The Press',
     'Газеты, которые можно читать, если повезёт. Первая оппозиция в новой '
     'стране появилась раньше, чем первая партия.',
     'Newspapers you can read if you are lucky. The first opposition in the new '
     'country appeared before the first party.'),
    ('kultura', ['pressa'], 40, 40,
     [('Happiness', 10), ('Stat', 'Stability', 0.05), ('Money', 2000)],
     'Культура', 'Culture',
     'Театр и выставка там, где раньше был только приказ. Город становится '
     'городом, а не точкой на карте.',
     'A theatre and an exhibition where before there was only an order. The city '
     'becomes a city, not a dot on a map.'),
    ('ideologiya', ['kultura'], 65, 30,
     [('Stat', 'Stability', 0.15), ('Happiness', -12), ('Modifier', 'Research', 0.1)],
     'Национальная идея', 'The National Idea',
     'Страна ищет то, что делает её отличной от матери, и находит: слишком '
     'много раз это уже делали.',
     'The country looks for what makes it unlike its mother and finds it: too '
     'many people have already done that.'),

    ('zapad', [], 25, 55,
     [('Stat', 'Stability', 0.1), ('Happiness', 6), ('Stat', 'Food', 1000)],
     'Земли на западе', 'The Western Lands',
     'Отделившаяся половина смотрит на карту и видит на ней соседей. Планы '
     'начинают составлять в ту же сторону.',
     'The seceded half looks at a map and sees neighbours on it. Plans begin to '
     'be drawn in that direction.'),
    ('baltika', ['zapad'], 35, 50,
     [('Annexation', '*', 1, 0.0), ('Stat', 'MilitaryPoints', 700), ('Money', 2500)],
     'Балтика', 'The Baltic',
     'Земли у воды, которые оставались в стороне от обоих расколов. Теперь у '
     'страны есть выход, который никто не оспаривает.',
     'Lands by the water that stayed aside from both splits. Now the country '
     'has a way out that nobody disputes.'),
    ('smolensk', ['baltika'], 40, 50,
     [('Annexation', '*', 1, 0.0), ('Stat', 'MilitaryPoints', 900), ('Stat', 'Food', 1500)],
     'Смоленщина', 'The Smolensk Land',
     'Первая земля, взятая у соседа, а не полученная. Страна начинает воевать и '
     'постепенно перестаёт считать это новостью.',
     'The first land taken from a neighbour rather than received. The country '
     'starts to wage war and gradually stops finding it news.'),
    ('moskovskaya', ['smolensk'], 45, 45,
     [('Annexation', '*', 2, 0.0), ('Stat', 'MilitaryPoints', 1400),
      ('Modifier', 'IncomeProduction', 0.1)],
     'Подмосковье', 'The Moscow Land',
     'Страна растёт в ту сторону, в которую её всё время смотрели, но не шли. '
     'Столица остаётся чужой, и теперь это обидно.',
     'The country grows in the direction it kept looking at but never moving. '
     'The capital stays someone else’s, and now it stings.'),
    ('vossoedinenie', ['moskovskaya'], 80, 30,
     [('Annexation', '*', 2, 0.0), ('Stat', 'MilitaryPoints', 2200),
      ('Stat', 'Stability', 0.1), ('Happiness', -10)],
     'Воссоединение', 'Reunification',
     'Республика идёт на восток и берёт то, что от неё когда-то отошло. '
     'Раскол закрывается, и страна оказывается в том размере, в котором её '
     'никто не ждал.',
     'The republic marches east and takes what once parted from it. The split '
     'closes, and the country turns out to be bigger than anyone expected.'),
])


def stat(name, value):
    return {'type': 'Stat', 'Name': name, 'Value': value}


def build_effects(items):
    out = []
    for it in items:
        if it[0] == 'Stat':
            out.append(stat(it[1], it[2]))
        elif it[0] == 'Money':
            out.append({'type': 'Money', 'Value': it[1]})
        elif it[0] == 'Happiness':
            out.append({'type': 'Happiness', 'Value': it[1]})
        elif it[0] == 'Modifier':
            out.append({'type': 'Modifier', 'Name': it[1], 'Value': it[2]})
        elif it[0] == 'Annexation':
            out.append({'type': 'Annexation', 'CivTag': it[1], 'Provinces': it[2],
                        'Share': it[3], 'War': True})
        else:
            raise ValueError('неизвестный эффект %r' % (it,))
    return out


def build_tree(tag, rows):
    prefix, parent_civ = rows[0], rows[1]
    entries = rows[2]
    depth = {}
    for key, parents, turns, trend, eff, ru, en, ru_desc, en_desc in entries:
        depth[key] = 0 if not parents else 1 + max(depth.get(p, 0) for p in parents)
    names = {}
    focus_list = []
    for key, parents, turns, trend, eff, ru, en, ru_desc, en_desc in entries:
        name = prefix + key.upper()
        names[name] = (ru, en, ru_desc, en_desc)
        f = {
            'name': name,
            'present': [prefix + p.upper() for p in parents],
            'condition': [],
            'describe': name.replace('BE2_F_', 'BE2_FD_'),
            'icon': 'focus_%s_%s' % (tag, key),
            'effect': build_effects(eff),
            'FinishTurns': turns,
            'AI_Trend': trend,
        }
        if not parents:
            # корень доступен, когда страна уже появилась
            f['condition'] = [{'type': 'Civilization_Exist', 'CivTag': parent_civ}]
        elif depth[key] >= 2:
            f['condition'] = [{'type': 'Time_Reach', 'Month': 0, 'Day': 0,
                               'Year': BASE_YEAR + (depth[key] - 1) * 3}]
        focus_list.append(f)
    data = {'name': tag, 'CivTag': tag, 'FOCUS_LIST': focus_list}
    return data, names


def write_lang(tag, names):
    path = os.path.join(LANG_ROOT, 'focus_%s_ru.properties' % tag)
    lines = ['# Переводы дерева фокусов %s (страна появляется при гражданской войне).' % tag,
             '# Русский текст в ключе, английский - в ключе с _EN.',
             '']
    for name in sorted(names):
        ru, en, ru_desc, en_desc = names[name]
        lines.append('%s = %s' % (name, ru))
        lines.append('%s_EN = %s' % (name, en))
    lines.append('')
    for name in sorted(names):
        ru, en, ru_desc, en_desc = names[name]
        dname = name.replace('BE2_F_', 'BE2_FD_')
        lines.append('%s = %s' % (dname, ru_desc))
        lines.append('%s_EN = %s' % (dname, en_desc))
    with io.open(path, 'w', encoding='utf-8', newline='\n') as fh:
        fh.write('\n'.join(lines) + '\n')
    return path


def main():
    total = 0
    for tag in sorted(TREES):
        data, names = build_tree(tag, TREES[tag])
        fdir = os.path.join(FOCUS_ROOT, tag)
        if not os.path.isdir(fdir):
            os.makedirs(fdir)
        fpath = os.path.join(fdir, '%s_focus.json' % tag)
        with io.open(fpath, 'w', encoding='utf-8', newline='\n') as fh:
            json.dump(data, fh, ensure_ascii=False, indent=2)
            fh.write('\n')
        lpath = write_lang(tag, names)
        print('    %-6s фокусов %2d  %s  %s' % (
            tag, len(data['FOCUS_LIST']),
            os.path.relpath(fpath, ROOT), os.path.relpath(lpath, ROOT)))
        total += len(data['FOCUS_LIST'])
    print('    всего фокусов: %d' % total)
    return 0


if __name__ == '__main__':
    sys.exit(main())
