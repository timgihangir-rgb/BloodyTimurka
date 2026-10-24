# -*- coding: utf-8 -*-
"""Собирает дерево фокусов фашистской Англии (eng_f) и его переводы.

Страна появляется после гражданской войны в английском дереве: игрок
начинает играть за неё с южными территориями и с Автократией. Дерево
поэтому начинается с того, что ещё не решено - собрать партию, поднять
милицию, запустить пропаганду, - и кончается тем, что Лондон и острова
берутся обратно.

Запуск:
    python3 tools/make_eng_f_tree.py
"""
import io
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
FOCUS_DIR = os.path.join(ROOT, 'data', 'focus', 'eng_f')
LANG = os.path.join(ROOT, 'data', 'lang', 'focus_eng_f_ru.properties')

CIV_TAG = 'eng_f'
PREFIX = 'BE2_F_ENGF_'

# key, предки, ходы, AI-вес, эффекты, RU-название, EN-название, RU-описание,
# EN-описание
TREE = [
    # ---------- ветка партии и государства ----------
    ('party', [], 25, 60,
     [('Stat', 'Stability', 0.15), ('Stat', 'ManPower', 60000),
      ('Stat', 'MilitaryPoints', 400), ('Happiness', 5)],
     'Съезд партии', 'Party Congress',
     'Сотни ячеек сходятся в зале на юге: первый съезд выбирает вождя, '
     'принимает устав и объявляет мятеж не отменённым, а переходом к власти. '
     'Малому острову нужна настоящая партия, иначе это просто банда.',
     'Hundreds of cells gather in a hall in the south: the first congress '
     'elects a leader, adopts a charter and declares the revolt not cancelled '
     'but begun. A small island needs a real party, or it is just a gang.'),
    ('charter', ['party'], 30, 55,
     [('Stat', 'Stability', 0.12), ('Money', 2000), ('Happiness', 5)],
     'Устав партии', 'Party Charter',
     'Писаные правила: кто решает, кто подчиняется, что делать с теми, кто не '
     'согласен. Вместо кучки людей с оружием получается организация, которой '
     'можно управлять и которая переживёт вождя.',
     'Written rules: who decides, who obeys, what to do with those who disagree. '
     'Instead of armed men you get an organisation that can be run and will '
     'outlive its leader.'),
    ('parliament', ['charter'], 40, 50,
     [('Stat', 'Stability', 0.1), ('Stat', 'TaxationLevel', 1),
      ('Stat', 'DiplomacyPoints', 300), ('Happiness', 5)],
     'Созыв парламента', 'Parliament Called',
     'Из Лондона приезжают депутаты, которых никто не звал, но слушать их '
     'приходится: новый юг берёт себе имя старой страны и часть её парламентской '
     'формы. Ремонт дорог дороже, чем их уважение.',
     'Deputies arrive from London uninvited, yet they must be listened to: the '
     'new south takes the name of the old country and part of its parliamentary '
     'form. Repairing roads costs more than their respect.'),
    ('oneparty', ['parliament'], 45, 45,
     [('Stat', 'Stability', 0.15), ('Happiness', -8), ('Stat', 'MilitaryPoints', 800)],
     'Единопартийное государство', 'One-Party State',
     'Остальные партии закрываются, газеты переходят под контроль, выборы '
     'становятся формой. Страна получает внутренний мир и перестаёт слышать '
     'себя - зато любая задача теперь решается сразу.',
     'The other parties are closed, newspapers come under control, elections '
     'become a formality. The country gets internal peace and stops hearing '
     'itself - but now any task is solved at once.'),
    ('gestapo', ['oneparty'], 40, 40,
     [('Stat', 'Stability', 0.12), ('Happiness', -6), ('Stat', 'MilitaryPoints', 600)],
     'Тайная полиция', 'Secret Police',
     'Управление страной берут на себя те, кого никто не назначал и никто не '
     'уволит. Недовольных больше не слышно, и это принимают за порядок.',
     'Running the country is taken over by those nobody appointed and nobody can '
     'dismiss. The dissatisfied are no longer heard, and that is taken for order.'),
    ('foreign', ['gestapo'], 35, 45,
     [('Stat', 'DiplomacyPoints', 800), ('Stat', 'Stability', 0.05)],
     'Министерство иностранных дел', 'Ministry of Foreign Affairs',
     'Юг впервые говорит с соседями сам, а не через Лондон. Никто не признаёт '
     'его, но все хотят знать, чего он хочет.',
     'The south speaks to its neighbours itself for the first time, not through '
     'London. Nobody recognises it, but everybody wants to know what it wants.'),
    ('civilservice', ['foreign'], 50, 35,
     [('Stat', 'Stability', 0.1), ('Stat', 'TaxationLevel', 1), ('Money', 2000),
      ('Happiness', -5)],
     'Своя государственная служба', 'Its Own Civil Service',
     'Чиновников больше не назначают из Лондона, их берут на юге и учат заново. '
     'Новая страна перестаёт управляться изнутри чужой и начинает платить своим.',
     'Officials are no longer appointed from London; they are taken in the south '
     'and taught anew. The new country stops being run from inside another one '
     'and starts paying its own.'),

    # ---------- ветка армии ----------
    ('militia', [], 25, 65,
     [('Stat', 'MilitaryPoints', 700), ('Stat', 'ManPower', 50000), ('Happiness', 5)],
     'Южная милиция', 'Southern Militia',
     'Шахтёры, рыбаки и портовые докеры вступают в строй с лопатой и винтовкой. '
     'Армии нет, но есть люди, которые умеют держать строй и ненавидят врага '
     'лично.',
     'Miners, fishermen and dockers fall in with a shovel and a rifle. There is '
     'no army, but there are men who can hold a line and hate the enemy in person.'),
    ('uniform', ['militia'], 30, 55,
     [('Stat', 'MilitaryPoints', 600), ('Stat', 'ManPower', 40000), ('Happiness', 5)],
     'Единая форма', 'Uniform',
     'Одинаковая одежда, одинаковый знак, одинаковая команда. Милиция '
     'перестаёт быть сбродом добровольцев и становится войском.',
     'Same clothes, same badge, same orders. The militia stops being a mob of '
     'volunteers and becomes an army.'),
    ('conscript', ['uniform'], 45, 50,
     [('Stat', 'ManPower', 300000), ('Stat', 'MilitaryPoints', 1500),
      ('Stat', 'Stability', -0.05), ('Happiness', -10)],
     'Всеобщая воинская повинность', 'Universal Conscription',
     'Каждый мужчина страны получает номер и срок службы. Молодых становится '
     'меньше, а армия - настоящей, с резервистами и обученными офицерами.',
     'Every man in the country gets a number and a term of service. There are '
     'fewer young men, and the army is real, with reserves and trained officers.'),
    ('tanks', ['conscript'], 50, 45,
     [('Stat', 'MilitaryPoints', 2500), ('Money', -3000),
      ('Modifier', 'RecruitablePopulation', 0.2)],
     'Танковые войска', 'Tank Corps',
     'Домашние верфи, железные дороги и угольные шахты дают первые машины. '
     'Юг упирается в рельсы и в чужую границу, и решает проблему бронёй.',
     'Dry docks, railways and coal mines give the first machines. The south is '
     'stuck against rails and a foreign border, and solves it with armour.'),
    ('airforce', ['tanks'], 55, 45,
     [('Stat', 'MilitaryPoints', 2000), ('Money', -2500),
      ('Modifier', 'RecruitablePopulation', 0.15)],
     'Воздушные силы', 'Air Force',
     'Самолёты над каналами, по которым ещё вчера возили уголь. Армия '
     'перестаёт быть пехотой и начинает быть силой, у которой есть скорость.',
     'Aircraft over the canals that carried coal only yesterday. The army stops '
     'being infantry and becomes a force that has speed.'),
    ('navy', ['airforce'], 50, 40,
     [('Stat', 'MilitaryPoints', 1500), ('Money', -2000),
      ('Modifier', 'Research', 0.2)],
     'Флот', 'The Navy',
     'Клайдские доки и каналы, по которым ещё вчера везли уголь, становятся '
     'базой. Страна, у которой есть только суша, всегда проигрывает тем, у '
     'кого есть вода.',
     'Clyde docks and the canals that carried coal only yesterday become a base. '
     'A country with only land always loses to those who have water.'),
    ('atom', ['navy'], 90, 30,
     [('Stat', 'NuclearWeapons', 1), ('Money', -8000),
      ('Stat', 'MilitaryPoints', 3000)],
     'Атомный проект', 'Atomic Project',
     'Физики из бывших университетов обещают бомбу, которая сделает войну '
     'короткой. Денег не хватает сразу, и не хватит ещё долго.',
     'Physicists from former universities promise a bomb that will make war '
     'short. The money is short at once, and will stay short for long.'),

    # ---------- ветка пропаганды и хозяйства ----------
    ('radio', [], 30, 60,
     [('Stat', 'TaxationLevel', 1), ('Happiness', 8), ('Stat', 'Stability', 0.05)],
     'Радиопропаганда', 'Radio Propaganda',
     'Каждое утро голос из южных рупоров объясняет, что́ страна делает и почему '
     'это правильно. Даже те, кто не верит, узнают новости первыми.',
     'Every morning a voice from southern speakers explains what the country is '
     'doing and why it is right. Even the unbelievers get the news first.'),
    ('press', ['radio'], 35, 55,
     [('Stat', 'TaxationLevel', 1), ('Stat', 'Stability', 0.08), ('Happiness', -4)],
     'Государственная пресса', 'State Press',
     'Газеты перестают спорить между собой и начинают спорить с Лондоном. '
     'Тиражи растут, сдержанная пресса исчезает.',
     'Newspapers stop arguing with each other and start arguing with London. '
     'Circulations grow, restrained press disappears.'),
    ('industry', ['press'], 45, 50,
     [('Money', 4000), ('Modifier', 'IncomeProduction', 0.2),
      ('Stat', 'Stability', 0.05)],
     'Оборонные заводы', 'Defence Plants',
     'Верфи и арматурные мастерские становятся военными. Меньше рабочих рук, '
     'но каждый завод делает то, что нужно армии, а не то, что продаётся.',
     'Dry docks and machine shops turn military. Fewer working hands, but every '
     'plant makes what the army needs rather than what sells.'),
    ('wheels', ['industry'], 50, 45,
     [('Money', 3500), ('Modifier', 'IncomeProduction', 0.2),
      ('Modifier', 'EconomyGrowth', 0.1)],
     'Рельсы и станки', 'Rails and Machines',
     'Лондонские заказы приходят сюда уже не как указ, а как заказ. Рельсы, '
     'станки и мосты сращивают юг в одну страну, а не в набор портов.',
     'London orders now arrive not as decree but as purchase order. Rails, '
     'machines and bridges weld the south into one country, not a set of ports.'),
    ('food', ['wheels'], 40, 45,
     [('Stat', 'Food', 3000), ('Happiness', 10), ('Stat', 'TaxationLevel', 1)],
     'Снабжение стола', 'Bread and Salt',
     'Новый строй держится на сытом человеке: склады, пашни и резервы зерна '
     'должны пережить любую войну, которую юг сам же и начнёт.',
     'The new order stands on a fed man: warehouses, fields and grain reserves '
     'must survive any war the south itself starts.'),
    ('autarky', ['food'], 60, 40,
     [('Stat', 'Food', 4000), ('Money', -2000), ('Happiness', 8),
      ('Modifier', 'IncomeProduction', 0.1)],
     'Автаркия', 'Autarchy',
     'Юг решает, что ничего не покупает, а всё делает сам. Это дорого, '
     'неудобно и работает - с тех пор, как работает.',
     'The south decides to buy nothing and make everything. It is expensive, '
     'awkward, and it works - as long as it works.'),
    ('labourfront', ['autarky'], 45, 40,
     [('Modifier', 'EconomyGrowth', 0.15), ('Stat', 'Food', 2000),
      ('Happiness', 8), ('Stat', 'Stability', 0.05)],
     'Трудовой фронт', 'Labour Front',
     'Страна перестаёт спрашивать, сколько стоит рабочая сила, и решает, что '
     'она стоит ровно столько, сколько нужно. Уголь, рельсы и хлеб идут по '
     'одному плану, и план этот не пересматривают.',
     'The country stops asking what labour costs and decides it costs exactly '
     'what is needed. Coal, rails and bread follow one plan, and that plan is '
     'not reconsidered.'),

    # ---------- ветка идеологии ----------
    ('purge', [], 30, 55,
     [('Stat', 'Stability', 0.1), ('Happiness', -12), ('Stat', 'MilitaryPoints', 500)],
     'Чистка в рядах', 'Purge',
     'Из партии уходят те, кто сомневался вслух. Страна становится тише и '
     'управлять ей легче, но уйти теперь нельзя от слова "доктрина".',
     'Those who doubted aloud leave the party. The country gets quieter and '
     'easier to rule, but no one can leave over the word "doctrine" now.'),
    ('brownshirt', ['purge'], 35, 55,
     [('Stat', 'MilitaryPoints', 1000), ('Stat', 'Stability', 0.08),
      ('Modifier', 'RecruitablePopulation', 0.15)],
     'Коричневые рубашки', 'Brownshirts',
     'Отряды, которые охраняют собрания и разгоняют чужие, становятся силой, '
     'которую боятся больше, чем уважают. Страна начинает узнавать их манеру.',
     'Squads that guard meetings and break up rivals become a force feared more '
     'than respected. The country starts to recognise their manner.'),
    ('school', ['brownshirt'], 50, 45,
     [('Stat', 'Stability', 0.12), ('Money', 1500), ('Happiness', 5),
      ('Modifier', 'PopGrowth', -0.1)],
     'Школа нового человека', 'The New School',
     'Программа, гдеИстория начинается с мятежа, а география кончается границей. '
     'Через одно поколение страна будет другой, и она об этом не заметит.',
     'A curriculum where history starts with the revolt and geography ends at '
     'the border. In a generation the country will be different and will not '
     'notice.'),
    ('church', ['school'], 45, 40,
     [('Happiness', 12), ('Stat', 'Stability', 0.08), ('Money', 1000),
      ('Happiness', -6)],
     'Суд над церковью', 'The Church on Trial',
     'Колокола уходят под контроль, а приходы становятся местом, где очередь '
     'записывают заранее. Вера и расписание сливаются в один документ.',
     'Bells come under control and parishes become a place where the queue is '
     'booked in advance. Faith and timetable merge into one document.'),
    ('television', ['church'], 40, 40,
     [('Happiness', 6), ('Stat', 'Stability', 0.06), ('Money', 2500)],
     'Телевидение', 'Television',
     'Картинку можно не пустить, а голос ещё можно заглушить. Страна получает '
     'глаз, который смотрит в каждый дом, и дом, в который смотрят все.',
     'A picture can be cut off, a voice can still be jammed. The country gets an '
     'eye that looks into every home, and a home that is looked into by all.'),
    ('museum', ['television'], 40, 40,
     [('Happiness', 8), ('Stat', 'Stability', 0.07), ('Money', 1500)],
     'Музей нации', 'Museum of the Nation',
     'Из Лондона вывозят то, что нельзя оставить. Юг собирает своё: скелеты, '
     'пушки, карту юга и длинный зал, где всё стоит по своим причинам.',
     'What cannot be left in London is taken away. The south collects its own: '
     'skeletons, cannons, a map of the south, and a long hall where everything '
     'stands for its own reasons.'),
    ('race', ['museum'], 60, 30,
     [('Stat', 'Stability', 0.15), ('Happiness', -18), ('Stat', 'MilitaryPoints', 1200)],
     'Расовый закон', 'Racial Law',
     'Страна решает, кто в ней свой, а кто нет, и записывает это в устав. '
     'Строительство идёт быстрее, а страна перестаёт быть домом для всех, кто '
     'здесь жил.',
     'The country decides who belongs and who does not, and writes it into the '
     'charter. Building goes faster, and the country stops being a home for all '
     'who lived here.'),

    # ---------- ветка территорий ----------
    ('defence', [], 25, 60,
     [('Stat', 'Stability', 0.12), ('Stat', 'MilitaryPoints', 600), ('Happiness', 5)],
     'Южная оборона', 'Defence of the South',
     'Первый рубеж - от берега до Темзы - объявлен военной зоной и укреплён. '
     'Здесь новая страна начинается не с победы, а с того, что её не отдадут.',
     'The first line, from the shore to the Thames, is declared a military zone '
     'and fortified. Here the new country does not begin with a victory but with '
     'not giving ground.'),
    ('cornwall', ['defence'], 35, 50,
     [('Annexation', '*', 1, 0.0, True), ('Stat', 'MilitaryPoints', 800),
      ('Happiness', -5)],
     'Взятие Корнволла', 'Taking Cornwall',
     'Последний английский угол, который ещё смотрит на юг, присоединяют к '
     'новой стране. Каменные мысы закрывают южное крыло страны.',
     'The last English corner that still looks south is joined to the new '
     'country. The stone headlands close the southern flank.'),
    ('coast', ['cornwall'], 40, 50,
     [('Annexation', '*', 1, 0.0, True), ('Stat', 'MilitaryPoints', 1000),
      ('Stat', 'Stability', 0.05), ('Happiness', -5)],
     'Южное побережье', 'The Southern Coast',
     'Кент, Сассекс и Девон переходят под управление южной столицы. Страна '
     'получает порты и подтверждение, что юг - это её середина, а не хвост.',
     'Kent, Sussex and Devon come under the southern capital. The country gets '
     'ports and the confirmation that the south is its middle, not its tail.'),
    ('london', ['coast'], 60, 35,
     [('Annexation', '*', 2, 0.0, True), ('Stat', 'MilitaryPoints', 2000),
      ('Stat', 'Stability', 0.1), ('Happiness', -12)],
     'Марш на Лондон', 'March on London',
     'Река Темза - последняя линия, за которой всё ещё белый флаг. Юг идёт на '
     'север, и столица страны меняется. Весь мир смотрит, кто победил.',
     'The Thames is the last line behind which the white flag still flies. The '
     'south marches north and the capital changes. The whole world watches to '
     'see who won.'),
    ('wales', ['london'], 45, 40,
     [('Annexation', '*', 1, 0.0, True), ('Stat', 'MilitaryPoints', 1200),
      ('Happiness', -8)],
     'Уэльс', 'Wales',
     'Угольные долины решают вопрос с ресурсами. Уэльс присоединяется не '
     'побеждённый, а купленный, и этой сделке ещё предстоит расплатиться.',
     'The coal valleys settle the question of resources. Wales is joined not '
     'beaten but bought, and that bill is yet to come due.'),
    ('scotland', ['wales'], 55, 40,
     [('Annexation', '*', 2, 0.0, True), ('Stat', 'MilitaryPoints', 1800),
      ('Stat', 'Stability', 0.08), ('Happiness', -10)],
     'Шотландия', 'Scotland',
     'За Северным мостом страна становится островом по-настоящему. К этому '
     'моменту её форма правления не оставляет другого названия, чем то, что '
     'уже есть.',
     'Beyond the northern bridge the country becomes an island for real. By now '
     'its form of government leaves no other name than the one already used.'),
    ('islands', ['scotland'], 70, 35,
     [('Stat', 'MilitaryPoints', 1500), ('Money', 3000), ('Stat', 'Stability', 0.06),
      ('Happiness', -6)],
     'Острова', 'The Islands',
     'Мэн, Джерси и Оркней переходят под управление южной столицы. Остров '
     'наконец перестаёт быть страной, которую оскорбляют с другого острова.',
     'The Isle of Man, Jersey and the Orkneys come under the southern capital. '
     'The island finally stops being a country insulted from another island.'),
]


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
            # (метка, жертва, сколько, доля, война)
            e = {'type': 'Annexation', 'CivTag': it[1], 'Provinces': it[2],
                 'Share': it[3], 'War': it[4]}
            out.append(e)
        else:
            raise ValueError('неизвестный эффект %r' % (it,))
    return out


def main():
    if not os.path.isdir(FOCUS_DIR):
        os.makedirs(FOCUS_DIR)
    names = {}
    focus_list = []
    # страна появляется около 1950-го (раскол в дереве eng стоит именно на этом
    # году), поэтому глубина дерева привязана ко времени: чем дальше по ветке,
    # тем позже можно идти. Без этого фокусы можно было бы начать раньше, чем
    # страна появилась.
    BASE_YEAR = 1952
    depth = {}
    for key, parents, turns, trend, eff, ru, en, ru_desc, en_desc in TREE:
        depth[key] = 0 if not parents else 1 + max(depth.get(p, 0) for p in parents)
    for key, parents, turns, trend, eff, ru, en, ru_desc, en_desc in TREE:
        name = PREFIX + key.upper()
        names[name] = (ru, en, ru_desc, en_desc)
        f = {
            'name': name,
            'present': [PREFIX + p.upper() for p in parents],
            'condition': [],
            'describe': name.replace('BE2_F_', 'BE2_FD_'),
            'icon': 'focus_eng_' + key,
            'effect': build_effects(eff),
            'FinishTurns': turns,
            'AI_Trend': trend,
        }
        if not parents:
            # корни требуют, чтобы страна уже появилась
            f['condition'] = [{'type': 'Civilization_Exist', 'CivTag': 'eng'}]
        elif depth[key] >= 2:
            f['condition'] = [{
                'type': 'Time_Reach',
                'Year': BASE_YEAR + (depth[key] - 1) * 4,
                'Month': 0,
                'Day': 0,
            }]
        focus_list.append(f)

    data = {'name': CIV_TAG, 'CivTag': CIV_TAG, 'FOCUS_LIST': focus_list}
    path = os.path.join(FOCUS_DIR, '%s_focus.json' % CIV_TAG)
    with io.open(path, 'w', encoding='utf-8', newline='\n') as fh:
        json.dump(data, fh, ensure_ascii=False, indent=2)
        fh.write('\n')
    print('    %s: фокусов %d' % (os.path.relpath(path, ROOT), len(focus_list)))

    lines = ['# Переводы дерева фокусов фашистской Англии (eng_f).',
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
    with io.open(LANG, 'w', encoding='utf-8', newline='\n') as fh:
        fh.write('\n'.join(lines) + '\n')
    print('    %s: ключей %d' % (os.path.relpath(LANG, ROOT), len(names) * 4))
    return 0


if __name__ == '__main__':
    sys.exit(main())
