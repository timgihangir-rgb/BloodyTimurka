# -*- coding: utf-8 -*-
"""
Дерево фокусов Югославии (yugo) - 38 фокусов.

Югославия - стартовая страна, а не та, что появляется при расколе, поэтому
дерево устроено как у pol/ger_f: пять корневых фокусов (план, армия,
строительство, единство, наука), от них ветвится хозяйство, техника и
политика, а в конце - разрыв с Москвой, военная революция и война.

Поздние ветки внешней политики:
  - Пакт с Берлином (после "Договора с западом") - вступление в германскую
    ось вызывает восстание демократов на юго-востоке страны (Скопье, Ниш,
    Приштина, Битола); игрок продолжает партию за новую демократическую
    Югославию (тег yugo_d, та же ветка дерева лежит в data/focus/yugo_d).
  - Аннексия Албании (после "Балканского союза").
  - Развал Югославии (после "Конституции") - Словения, Хорватия и
    Черногория с югом откалываются эффектами CivilWar, остаётся Сербия.

Эффекты CivilWar создают страны строго по тегу вида <реальный>_<буква
идеологии>: cro_n, slv_n, mon_f (существующие флаги в игре) и yugo_d.
Имена новых стран дописаны в data/lang/new_civs.properties, дерева у них -
кроме yugo_d - нет: страны-республики достаются ИИ и в фокусы не играют.

Хронология привязана к 1938-1950-м, как у остальных деревьев: партия
начинается перед войной, и поздние фокусы открываются только когда страна
к этому времени доросла.

Запуск:  python3 tools/make_yugo_tree.py
"""
import io
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
FOCUS_ROOT = os.path.join(ROOT, 'data', 'focus')
LANG_ROOT = os.path.join(ROOT, 'data', 'lang')

TAG = 'yugo'
PREFIX = 'BE2_F_YUGO_'

# key, родители, ходы, AI-вес, эффекты, RU-название, EN-название,
# RU-описание, EN-описание
# Эффект: ('Stat', имя, значение) | ('Money', значение) | ('Happiness', значение)
#          ('Modifier', имя, значение) | ('Год', N) - условие Time_Reach
TREE = [
    # ---------------- пять корней ----------------
    ('plan5', [], 45, 50, [
        ('Stat', 'ManPower', 120000),
        ('Modifier', 'IncomeProduction', 0.2),
        ('Money', -600),
        ('Год', 1938),
    ], 'Пятилетний план', 'The Five-Year Plan',
       'Белград берёт темп: пять лет, пять отраслей, ни одного разговора о '
       'том, что будет, если план не выполнен. Каждый построенный завод '
       'считается отдельным подвигом, и отчёт о нём идёт в столицу рекой '
       'бумаги.',
       'Belgrade sets the pace: five years, five industries, not one word '
       'about what happens if the plan is not met. Every plant built counts '
       'as a feat of its own, and the report on it flows to the capital as '
       'a river of paper.'),

    ('vojska', [], 40, 50, [
        ('Stat', 'MilitaryPoints', 450),
        ('Stat', 'ManPower', 80000),
        ('Money', -400),
    ], 'Единая армия', 'The United Army',
       'Семь армий республик становятся одной. Корпуса сводят к общему '
       'уставу, мундиры шьют на одном складе, и офицеров из Белграда, '
       'Загреба и Любляны учат говорить приказания одинаково.',
       'Seven republic armies become one. Corps are drawn up under a common '
       'regulation, uniforms are cut from a single depot, and officers from '
       'Belgrade, Zagreb and Ljubljana are taught to give orders alike.'),

    ('grad', [], 40, 45, [
        ('Stat', 'ManPower', 100000),
        ('Money', -300),
        ('Happiness', 4),
    ], 'Новый Белград', 'New Belgrade',
       'Столица перестаёт помещаться в старых кварталах. На берегу Савы '
       'кладут первый камень, и город для всей страны становится '
       'доказательством, что федерация не сон.',
       'The capital no longer fits in the old quarters. The first stone is '
       'laid on the bank of the Sava, and for the whole country the city '
       'becomes proof that the federation is not a dream.'),

    ('jedinstvo', [], 35, 50, [
        ('Stat', 'Stability', 0.35),
        ('Modifier', 'IncomeTaxation', -0.1),
    ], 'Единство союза', 'Unity of the Union',
       'Словенцы, хорваты, сербы, черногорцы, македонцы, боснийцы и косовары '
       'живут в одном государстве и почти не понимают друг друга. Устав '
       'союзного собрания написан так, чтобы ни одна республика не '
       'почувствовала себя старшей.',
       'Slovenes, Croats, Serbs, Montenegrins, Macedonians, Bosnians and '
       'Kosovars live in one state and barely understand each other. The '
       'statute of the federal assembly is written so that no republic '
       'feels itself the elder.'),

    ('nauka', [], 40, 45, [
        ('Stat', 'MilitaryPoints', 250),
        ('Money', -350),
        ('Modifier', 'Research', 0.15),
    ], 'Наука и школа', 'Science and School',
       'Университеты Белграда, Загреба и Любляны получают общий бюджет, '
       'общие журналы и общий список профессоров, готовых переехать '
       'в чужой город.',
       'The universities of Belgrade, Zagreb and Ljubljana get a shared '
       'budget, shared journals, and a shared list of professors willing to '
       'move to another city.'),

    # ---------------- хозяйство ----------------
    ('petnol', ['plan5'], 50, 45, [
        ('Stat', 'ManPower', 100000),
        ('Money', -500),
        ('Modifier', 'IncomeProduction', 0.25),
    ], 'Нефть и уголь', 'Oil and Coal',
       'Нефтяники Славонии и угольщики Боснии переходят в ведение союзного '
       'министерства. Запасы страны наконец видны в одном отчёте.',
       'The oilmen of Slavonia and the coalmen of Bosnia come under the '
       'federal ministry. The reserves of the country are finally visible in '
       'a single report.'),

    ('stal', ['petnol'], 55, 45, [
        ('Modifier', 'IncomeProduction', 0.3),
        ('Stat', 'ManPower', 90000),
        ('Money', -700),
    ], 'Металлургия', 'The Steelworks',
       'Заводы в Зенице, Смедерево и Нише работают на общую закупку сырья '
       'и общий сбыт. Директоров в Белграде меняют, не спрашивая никого '
       'из них.',
       'The plants at Zenica, Smederevo and Nis buy their ore together and '
       'sell together. The directors in Belgrade are changed without asking '
       'any of them.'),

    ('elektro', ['stal'], 50, 45, [
        ('Modifier', 'IncomeProduction', 0.2),
        ('Modifier', 'EconomyGrowth', 0.2),
        ('Stat', 'MilitaryPoints', 200),
    ], 'Гидроэлектростанции', 'The Hydro Stations',
       'Запруды на Дрине и в Далмации светят половине страны. Ток течёт '
       'по линиям, которые тянули через три границы, потому что одну '
       'станцию построить дешевле, чем три.',
       'The dams on the Drina and in Dalmatia light half the country. The '
       'current runs along lines that crossed three borders, because one '
       'station is cheaper to build than three.'),

    ('zeleznica', ['grad'], 45, 50, [
        ('Modifier', 'IncomeProduction', 0.2),
        ('Modifier', 'MovementPoints', 0.15),
        ('Money', -400),
    ], 'Сеть дорог', 'The Railway Net',
       'Белград, Загреб и Скопье соединяют одной сетью. Вагоны идут по '
       'общему расписанию, и пассажир на границе больше не ищет свой '
       'поезд в другом окне.',
       'Belgrade, Zagreb and Skopje are joined by one net. Cars run on one '
       'timetable, and a passenger at the border no longer looks for his '
       'train in another window.'),

    ('port', ['zeleznica', 'elektro'], 50, 40, [
        ('Stat', 'MilitaryPoints', 350),
        ('Modifier', 'IncomeTaxation', 0.2),
        ('Money', 700),
    ], 'Порт и торговля', 'The Port and the Trade',
       'Риека и Копер переходят под союзную морскую службу. Страна учится '
       'грузить и выгружать сама, а не платить чужим перевозчикам за то '
       'же самое.',
       'Rijeka and Koper come under the federal maritime service. The country '
       'learns to load and unload itself instead of paying foreign carriers '
       'for the same work.'),

    ('poloprim', ['petnol'], 45, 40, [
        ('Modifier', 'PopGrowth', 0.2),
        ('Stat', 'Food', 250000),
        ('Happiness', 3),
    ], 'Хлеб и земля', 'Bread and Land',
       'Земельные фонды республик сводят в один, и наделы между '
       'Воеводиной, Прилепом и Косовом распределяют по одной системе.',
       'The land funds of the republics are merged into one, and allotments '
       'between the Vojvodina, Prilep and Kosovo are distributed by a single '
       'system.'),

    # ---------------- техника ----------------
    ('vojska_tech', ['vojska'], 50, 45, [
        ('Stat', 'MilitaryPoints', 400),
        ('Modifier', 'Research', 0.2),
        ('Money', -600),
    ], 'Школа офицеров', 'The Officers\' School',
       'Военные училища всех республик сливаются в одно. Курсанты из '
       'Приштины и Скопья впервые слушают одну лекцию и сдают один '
       'экзамен.',
       'The military academies of all republics merge into one. Cadets from '
       'Pristina and Skopje for the first time hear one lecture and sit one '
       'exam.'),

    ('broni', ['vojska_tech'], 55, 45, [
        ('Stat', 'MilitaryPoints', 500),
        ('Stat', 'ManPower', 60000),
        ('Money', -700),
    ], 'Оружейные заводы', 'The Arms Plants',
       'Пушечные заводы в Нише и Крагуевце переходят на союзный заказ. '
       'Война, если случится, будет вооружена не чужими поставками, '
       'а собственными стволами.',
       'The gun plants at Nis and Kragujevac take federal orders. The war, '
       'if it comes, will be armed by its own barrels rather than by foreign '
       'deliveries.'),

    ('brzdl', ['broni'], 55, 40, [
        ('Stat', 'MovePoints', 3),
        ('Stat', 'MilitaryPoints', 450),
    ], 'Бронетехника', 'Armour',
       'Броневой лист в Нише и Ужице идёт на танки, а не на тракторы для '
       'сельских работ. Завод переводят на военный заказ, и крестьяне '
       'жалуются, что тракторы больше не делают.',
       'The armour plate at Nis and Uzice goes into tanks instead of farm '
       'tractors. The works is switched to war orders, and the peasants '
       'complain that no more tractors are made.'),

    ('aviacija', ['vojska_tech'], 55, 40, [
        ('Stat', 'MilitaryPoints', 350),
        ('Modifier', 'Research', 0.15),
        ('Money', -500),
    ], 'Авиация', 'The Air Force',
       'Под Белградом открывают первый союзный аэродром, и страна получает '
       'то, чего у неё не было ни одного дня: собственную авиацию.',
       'The first federal airfield opens near Belgrade, and the country '
       'gets the thing it never had for a single day: an air force of its '
       'own.'),

    ('flota', ['port', 'broni'], 55, 40, [
        ('Stat', 'MilitaryPoints', 400),
        ('Modifier', 'IncomeProduction', 0.15),
        ('Money', -500),
    ], 'Флот', 'The Fleet',
       'Два республиканских флота срастаются в один. Средиземноморье '
       'перестаёт быть чужим берегом.',
       'The two republic fleets grow into one. The Mediterranean stops being '
       'someone else\'s shore.'),

    # ---------------- политика ----------------
    ('ustav', ['jedinstvo'], 50, 50, [
        ('Stat', 'Stability', 0.2),
        ('Stat', 'DiplomacyPoints', 200),
    ], 'Устав союза', 'The Statute of the Union',
       'Пишут устав, который заменит конституцию. Он длиннее любой '
       'европейской конституции, и в нём ни разу не сказано, кто главнее.',
       'They write a statute to stand in for a constitution. It is longer '
       'than any constitution in Europe, and it never says who is the senior '
       'partner.'),

    ('sabor', ['ustav'], 50, 45, [
        ('Modifier', 'EconomyGrowth', 0.15),
        ('Stat', 'Stability', 50),
        ('Happiness', 5),
    ], 'Народное собрание', 'The National Assembly',
       'Союзное собрание переезжает в Белград и начинает решать всё, от '
       'судьбы Хорватии до цены на сахар. Заседания идут долго, решения '
       'принимают в обед.',
       'The federal assembly moves to Belgrade and begins to decide '
       'everything, from the fate of Croatia to the price of sugar. The '
       'sessions are long, and the decisions are made at lunch.'),

    ('sud', ['sabor'], 45, 40, [
        ('Modifier', 'Administration', 0.2),
        ('Stat', 'Stability', 40),
    ], 'Суды и администрация', 'The Courts and the Administration',
       'Суды республик становятся частью союзной системы. Дело, начатое '
       'в Скопье, можно теперь оспорить в Загребе, и бумаги едут тем же '
       'коридором, что и санитарные.',
       'The republic courts become part of the federal system. A case opened '
       'in Skopje can now be appealed in Zagreb, and the papers travel along '
       'the same corridor as the sanitary ones.'),

    ('partija', ['sabor'], 50, 45, [
        ('Modifier', 'MilitaryUpkeep', -0.1),
        ('Stat', 'ManPower', 80000),
        ('Happiness', -4),
    ], 'Единая партия', 'The United Party',
       'Партии республик сливаются в одну. Тот, кто не вступил, больше не '
       'может занимать должность, и учителя узнают об этом раньше учеников.',
       'The republic parties merge into one. Those who did not join can no '
       'longer hold office, and the teachers learn about it before the pupils.'),

    ('profsoyu', ['partija', 'stal'], 50, 40, [
        ('Modifier', 'EconomyGrowth', 0.15),
        ('Happiness', -3),
    ], 'Профсоюзы', 'The Trade Unions',
       'Профсоюзы становятся единственной силой, которую признают и '
       'заводчики, и бюрократы. Они требуют прибавки, её не дают, и '
       'забастовка становится частью рабочего года.',
       'The trade unions become the one force that both the factory owners '
       'and the bureaucrats recognise. They ask for a raise, they are '
       'refused, and the strike becomes part of the working year.'),

    # ---------------- иностранные связи ----------------
    ('zapad', ['ustav'], 50, 45, [
        ('Modifier', 'EconomyGrowth', 0.2),
        ('Stat', 'DiplomacyPoints', 250),
    ], 'Договор с западом', 'The Western Agreement',
       'Уголь и медь идут на запад, деньги приходят оттуда же, и страна '
       'постепенно перестаёт смотреть на восток. Это называется '
       'нейтралитетом, и нейтралитет кончается вместе с договором.',
       'Coal and copper go west, money comes from there too, and the country '
       'slowly stops looking east. This is called neutrality, and neutrality '
       'ends together with the agreement.'),

    ('vostok', ['broni'], 50, 40, [
        ('Modifier', 'MilitaryUpkeep', -0.15),
        ('Stat', 'MilitaryPoints', 350),
        ('Happiness', -3),
    ], 'Договор с востоком', 'The Eastern Agreement',
       'Оружейные поставки идут в обе стороны, и страна берёт кредит под '
       'те же обязательства. Говорят: подписано. И о том, что будет после, '
       'не говорит никто.',
       'Arms flow in both directions, and the country takes a loan against '
       'the same commitments. They say it was signed. And nobody says what '
       'comes after.'),

    ('balkan', ['zapad', 'sud'], 55, 40, [
        ('Modifier', 'Research', 0.15),
        ('Stat', 'DiplomacyPoints', 300),
        ('Money', -400),
    ], 'Балканский союз', 'The Balkan Pact',
       'Белград сажает соседей за стол и предлагает всем одну и ту же '
       'границу. Страны Балкан соглашаются на словах и не соглашаются '
       'на карте.',
       'Belgrade seats its neighbours at one table and offers them all the '
       'same border. The Balkan states agree in words and do not agree on '
       'the map.'),

    # ---------------- распад и внешние авантюры ----------------
    ('os', ['zapad'], 60, 30, [
        ('Relation', 'ger_f', 0.45),
        ('Relation', 'ita', 0.3),
        ('Money', -400),
        ('Happiness', -5),
        ('Stat', 'Stability', -35),
        ('CivilWar', {
            'NewCivTag': 'yugo_d',
            'RebelGroup': 'yugo',
            'Government': 'DirectDemocracy',
            'Cities': ['Skopje', 'Nis', 'Prishtina', 'Bitola'],
            'Connect': True,
            'TakeControl': True,
        }),
        ('Год', 1940),
    ], 'Пакт с Берлином', 'The Berlin Pact',
       'В Берлине подписывают протокол, который называют защитой от любых '
       'неожиданностей. Сразу после подписи на юго-востоке страны '
       'поднимается восстание демократов: они считают, что неожиданность '
       'уже случилась. Восставшие занимают Скопье, Ниш, Приштину и Битолу '
       'и объявляют себя настоящей Югославией.',
       'A protocol is signed in Berlin, called protection against any '
       'surprises. Right after the signature a rising of democrats starts '
       'in the south-east of the country: they believe the surprise has '
       'already happened. The rebels take Skopje, Nis, Pristina and Bitola '
       'and declare themselves the true Yugoslavia.'),

    ('albania', ['balkan'], 50, 40, [
        ('Annexation', 'alb2', 99, 0.0, True),
        ('Annexation', 'alb', 99, 0.0, True),
        ('Money', -500),
        ('Happiness', -8),
        ('Stat', 'Stability', -30),
        ('Relation', '*', -0.1),
    ], 'Аннексия Албании', 'The Annexation of Albania',
       'Тирану занимают без долгих переговоров. Албанскую границу '
       'переносят, страну переписывают в союзную провинцию, и школьники '
       'в Приштине учат, что море начинается прямо от их порога.',
       'Tirana is taken without long negotiations. The Albanian border is '
       'moved, the country is re-written as a federal province, and school '
       'children in Pristina learn that the sea begins right at their '
       'doorstep.'),

    ('razval', ['konstitutsiya'], 70, 30, [
        ('CivilWar', {
            'NewCivTag': 'slv_n',
            'RebelGroup': 'yugo',
            'Government': 'ConstuntionalMonarchy',
            'From': 'north',
            'Band': 0.2,
            'TakeControl': False,
        }),
        ('CivilWar', {
            'NewCivTag': 'cro_n',
            'RebelGroup': 'yugo',
            'Government': 'ConstuntionalMonarchy',
            'From': 'west',
            'Band': 0.35,
            'TakeControl': False,
        }),
        ('CivilWar', {
            'NewCivTag': 'mon_f',
            'RebelGroup': 'yugo',
            'Government': 'Autocracy',
            'From': 'south',
            'Band': 0.25,
            'TakeControl': False,
        }),
        ('Stat', 'Stability', -80),
        ('Happiness', -10),
        ('Money', -600),
        ('Год', 1948),
    ], 'Развал Югославии', 'The Dissolution of Yugoslavia',
       'Союз распадается по швам: Словения уходит на север, Хорватия - '
       'на запад, за ними тянется Черногория со всем югом. В Белграде '
       'остаётся урезанное государство, которое никто больше не называет '
       'Югославией, кроме тех, кто при ней остался.',
       'The union falls apart: Slovenia leaves to the north, Croatia to '
       'the west, and Montenegro trails after them with the whole south. '
       'In Belgrade a shrunken state remains, which nobody calls '
       'Yugoslavia any more, except those who stayed with it.'),

    # ---------------- поздняя история ----------------
    ('vyselid', ['grad'], 50, 40, [
        ('Stat', 'Population', 300000),
        ('Money', -500),
        ('Modifier', 'Administration', 0.2),
    ], 'Переселенцы', 'The Settlers',
       'Сербы, хорваты и мусульмане едут на новые земли, и республики '
       'получают людей вместо тех, кто уехал. Каждая область заселена '
       'своими, и через двадцать лет это перестанет быть заметно.',
       'Serbs, Croats and Muslims move onto new land, and the republics get '
       'people in place of those who left. Each region is settled with its '
       'own kind, and in twenty years this will no longer be noticeable.'),

    ('konstitutsiya', ['ustav', 'sabor'], 60, 40, [
        ('Stat', 'Stability', 0.25),
        ('Stat', 'DiplomacyPoints', 200),
        ('Год', 1946),
    ], 'Конституция', 'The Constitution',
       'Устав дописывают до конституции. Первая статья гласит, что '
       'Югославия - союзное государство, и эта статья дороже всех '
       'остальных вместе взятых.',
       'The statute is finished off into a constitution. The first article '
       'says that Yugoslavia is a federal state, and that article costs '
       'more than all the others put together.'),

    ('voen_revol', ['partija', 'broni', 'flota'], 70, 35, [
        ('Stat', 'MilitaryPoints', 800),
        ('Modifier', 'MilitaryUpkeep', -0.2),
        ('Stat', 'Stability', -80),
        ('Happiness', -8),
        ('Год', 1941),
    ], 'Военная революция', 'The Military Revolution',
       'Генералы решают, что страна движется слишком медленно. Белград '
       'сменяют, партию распускают, а договоры с соседями пересматривают '
       'за ночь, пока идёт мобилизация.',
       'The generals decide the country is moving too slowly. Belgrade is '
       'changed, the party is dissolved, and the treaties with the '
       'neighbours are redone overnight while the mobilisation runs.'),

    ('rossiya', ['vostok', 'konstitutsiya'], 70, 35, [
        ('Stat', 'Stability', -60),
        ('Modifier', 'MilitaryUpkeep', -0.15),
        ('Happiness', -5),
        ('Год', 1948),
    ], 'Разрыв с Москвой', 'The Break with Moscow',
       'В Белград приезжают люди, у которых есть своё мнение о том, кому '
       'принадлежит страна, и разговор идёт именно об этом. Приехавшие '
       'уезжают ни с чем.',
       'People arrive in Belgrade with their own opinion about who the '
       'country belongs to, and the conversation is exactly about that. '
       'Those who came leave with nothing.'),

    ('voina', ['rossiya', 'voen_revol', 'konstitutsiya'], 85, 30, [
        ('Stat', 'ManPower', 400000),
        ('Modifier', 'MilitaryUpkeep', 0.2),
        ('Stat', 'Stability', -100),
        ('Happiness', -15),
        ('Год', 1950),
    ], 'Война', 'The War',
       'Страна идёт войной и проигрывает её за шесть недель. От прежней '
       'Югославии остаются горные деревни, несколько заводов и очень '
       'длинный список взаимных претензий.',
       'The country goes to war and loses it in six weeks. Of the former '
       'Yugoslavia there remain mountain villages, a few plants, and a very '
       'long list of mutual claims.'),

    ('vremya', ['brzdl', 'elektro'], 50, 40, [
        ('Modifier', 'Research', 0.25),
        ('Stat', 'MilitaryPoints', 200),
        ('Happiness', 4),
        ('Год', 1945),
    ], 'Своя техника', 'Its Own Technology',
       'Страна перестаёт покупать чужую технику и начинает производить '
       'свою. Это дороже и хуже, зато на вопрос «чья это марка» есть '
       'ответ, и ответ без запинки.',
       'The country stops buying foreign machinery and starts making its '
       'own. It is dearer and worse, but the question of whose make it is '
       'has an answer, and the answer comes without hesitation.'),

    ('svoboda', ['sabor', 'sud', 'nauka'], 60, 40, [
        ('Stat', 'Stability', 0.35),
        ('Happiness', 8),
        ('Год', 1947),
    ], 'Свобода и порядок', 'Freedom and Order',
       'Границы между республиками упраздняют, валюту делают общей, и '
       'человек, переехавший из Скопья в Любляну, не чувствует себя '
       'иностранцем. Это стоит дорого и длится недолго.',
       'The borders between the republics are abolished, the currency is '
       'made common, and a man who moves from Skopje to Ljubljana does not '
       'feel like a foreigner. It is expensive, and it does not last.'),

    ('nasledie', ['konstitutsiya'], 55, 35, [
        ('Modifier', 'EconomyGrowth', 0.2),
        ('Stat', 'Population', 200000),
    ], 'Наследие', 'The Legacy',
       'Страна получает название, которое переживёт её саму. Славу, о '
       'которой в чужих странах будут читать как о чужой.',
       'The country gets a name that will outlive it. A glory that will be '
       'read in other countries as somebody else\'s.'),

    ('gora', ['vyselid', 'balkan'], 55, 40, [
        ('Stat', 'ManPower', 150000),
        ('Modifier', 'DefenseBonus', 0.3),
        ('Happiness', 5),
    ], 'Горная оборона', 'The Mountain Defence',
       'Горы, в которых живут сербы, албанцы и хорваты, объявляют '
       'стратегической линией. Солдаты сюда не проходят, и в этом смысле '
       'горы надёжнее конституции.',
       'The mountains where Serbs, Albanians and Croats live are declared a '
       'strategic line. Soldiers do not come through here, and in that '
       'sense the mountains are more reliable than the constitution.'),

    ('rynok', ['port', 'poloprim'], 50, 35, [
        ('Modifier', 'IncomeProduction', 0.3),
        ('Stat', 'Food', 150000),
        ('Money', 500),
    ], 'Общий рынок', 'The Common Market',
       'Таможни между республиками упраздняют, и товар идёт через всю '
       'страну свободно. Свои производители жалуются на дешёвый импорт, '
       'и жалобы доходят до Белграда вовремя.',
       'The customs posts between the republics are abolished, and goods move '
       'freely across the whole country. Local producers complain about '
       'cheap imports, and the complaints reach Belgrade in time.'),

    ('zvezda', ['nasledie', 'svoboda'], 60, 35, [
        ('Stat', 'Stability', 0.2),
        ('Stat', 'DiplomacyPoints', 300),
        ('Happiness', 6),
        ('Год', 1955),
    ], 'Звезда над Балканами', 'A Star Over the Balkans',
       'Флаг страны вешают над каждой школой, и дети выучивают название '
       'государства раньше названия своего города. Некоторые из них потом '
       'разрушат это государство и переживут его.',
       'The flag goes up over every school, and the children learn the name '
       'of the state before the name of their own city. Some of them will '
       'later destroy the state and outlive it.'),
]


def build_effects(items):
    out = []
    for it in items:
        if it[0] == 'Stat':
            out.append({'type': 'Stat', 'Name': it[1], 'Value': it[2]})
        elif it[0] == 'Money':
            out.append({'type': 'Money', 'Value': it[1]})
        elif it[0] == 'Happiness':
            out.append({'type': 'Happiness', 'Value': it[1]})
        elif it[0] == 'Modifier':
            out.append({'type': 'Modifier', 'Name': it[1], 'Value': it[2]})
        elif it[0] == 'Relation':
            out.append({'type': 'Relation', 'CivTag': it[1], 'Relation': it[2]})
        elif it[0] == 'Annexation':
            # тег жертвы, сколько провинций, доля нашей нации, с войной ли
            _, civ, provinces, share, war = it
            out.append({'type': 'Annexation', 'CivTag': civ, 'Provinces': provinces,
                        'Share': share, 'War': war})
        elif it[0] == 'CivilWar':
            # словарь с параметрами раскола: NewCivTag, RebelGroup, Government,
            # Cities/From/Band, TakeControl и т.п.
            out.append({'type': 'CivilWar'})
            out[-1].update(it[1])
        else:
            raise ValueError('неизвестный эффект %r' % (it,))
    return out


def build_tree():
    depth = {}
    for key, parents, _t, _w, _e, _ru, _en, _rd, _ed in TREE:
        depth[key] = 0 if not parents else 1 + max(depth.get(p, 0) for p in parents)

    names = {}
    focus_list = []
    for key, parents, turns, trend, eff, ru, en, ru_desc, en_desc in TREE:
        name = PREFIX + key.upper()
        names[name] = (ru, en, ru_desc, en_desc)
        # год из эффектов уходит в условие, а не в effect
        year = None
        clean = []
        for e in eff:
            if e[0] == 'Год':
                year = e[1]
            else:
                clean.append(e)
        cond = []
        if year is not None:
            cond.append({'type': 'Time_Reach', 'Month': 0, 'Day': 0, 'Year': year})
        f = {
            'name': name,
            'present': [PREFIX + p.upper() for p in parents],
            'condition': cond,
            'describe': name.replace('BE2_F_', 'BE2_FD_'),
            'icon': 'focus_%s_%s' % (TAG, key),
            'effect': build_effects(clean),
            'FinishTurns': turns,
            'AI_Trend': trend,
        }
        focus_list.append(f)
    return {'name': 'Югославия', 'CivTag': TAG, 'FOCUS_LIST': focus_list}, names


def write_lang(names):
    path = os.path.join(LANG_ROOT, 'focus_%s_ru.properties' % TAG)
    lines = ['# Переводы дерева фокусов %s (стартовая страна).' % TAG,
             '# Русский текст в ключе, английский - в ключе с _EN.',
             '']
    for name in sorted(names):
        ru, en, _rd, _ed = names[name]
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
    data, names = build_tree()
    fdir = os.path.join(FOCUS_ROOT, TAG)
    if not os.path.isdir(fdir):
        os.makedirs(fdir)
    fpath = os.path.join(fdir, '%s_focus.json' % TAG)
    with io.open(fpath, 'w', encoding='utf-8', newline='\n') as fh:
        json.dump(data, fh, ensure_ascii=False, indent=2)
        fh.write('\n')
    lpath = write_lang(names)
    roots = sum(1 for f in data['FOCUS_LIST'] if not f['present'])
    print('    %-6s фокусов %2d (корней %d)  %s  %s' % (
        TAG, len(data['FOCUS_LIST']), roots,
        os.path.relpath(fpath, ROOT), os.path.relpath(lpath, ROOT)))

    # Демократическая Югославия (появляется после "Пакта с Берлином")
    # продолжает партию тем же деревом: страна без фокусов не получит
    # управление от CivilWar (TakeControl), поэтому дерево нужно.
    dtag = TAG + '_d'
    ddata = dict(data)
    ddata['CivTag'] = dtag
    dfdir = os.path.join(FOCUS_ROOT, dtag)
    if not os.path.isdir(dfdir):
        os.makedirs(dfdir)
    dpath = os.path.join(dfdir, '%s_focus.json' % dtag)
    with io.open(dpath, 'w', encoding='utf-8', newline='\n') as fh:
        json.dump(ddata, fh, ensure_ascii=False, indent=2)
        fh.write('\n')
    print('    %-6s то же дерево  %s' % (dtag, os.path.relpath(dpath, ROOT)))
    return 0


if __name__ == '__main__':
    sys.exit(main())