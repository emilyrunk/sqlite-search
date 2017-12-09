import sqlite3
import sys
from pprint import pprint
con = sqlite3.connect('dict.sqlite3.db')
cur = con.cursor()


dictdict = {
    'poop0':'poopy butt farts0',
    'poop1':'poopy butt farts1',
    'poop2':'poopy butt farts2',
    'poop3':'poopy butt farts3',
    'poop4':'poopy butt farts4',
    'poop5':'poopy butt farts5',
    'poop6':'poopy butt farts6',
    'poop7':'poopy butt farts7',
    'poop8':'poopy butt farts8',
    'poop9':'poopy butt farts9',
}

def insert_word(word, defin):
    cur.execute("INSERT INTO dict(word, defin) VALUES('{0}','{1}')".format(word, defin))

def create_word_table():
    cur.execute("CREATE TABLE dict (d_id INTEGER PRIMARY KEY AUTOINCREMENT,word TEXT NOT NULL,defin TEXT NOT NULL)")


def insert_all_data():
    for word, defin in dictdict.items():
        insert_word(word, defin)

def print_all_data():
    cursor = cur.execute("SELECT * FROM dict")
    for row in cursor:
        pprint(row)

def delete_all_data():
    cur.execute("DELETE FROM dict")


def main():
    pprint(sys.argv)
    if len(sys.argv) == 2 and sys.argv[1] == "-p":
        print_all_data()
    elif len(sys.argv) == 2 and sys.argv[1] == "-d":
        delete_all_data()
    elif len(sys.argv) == 4 and sys.argv[1] == "-a":
        insert_word(sys.argv[2], sys.argv[3])
    elif len(sys.argv) == 2 and sys.argv[1] == "-l":
        create_word_table()
        insert_all_data()
    else:
        print('Didn put nun cmds!')



if __name__ == '__main__':
    main()
    con.commit()
    con.close()


# cur.execute("CREATE TABLE PRODUCT (p_id INTEGER PRIMARY KEY AUTOINCREMENT,p_name TEXT NOT NULL,price REAL,quantity INTEGER)")
# print ("Table created!!!")
# con.close()


# cur.execute("INSERT INTO Product(p_name,price,quantity) VALUES('AutoCAD',200,20)");
# cur.execute("INSERT INTO Product(p_name,price,quantity) VALUES('Quick Hill',330,12)");
# cur.execute("INSERT INTO Product(p_name,price,quantity) VALUES('Keyboard',250,25)");
# cur.execute("INSERT INTO Product(p_name,price,quantity) VALUES('Mouse',150,34)");
# print "Data Inserted!!!";
# con.commit()
# con.close()

# print("p_id\t p_name \t price \t quantity")
# cursor = cur.execute("SELECT * FROM Product")
# for row in cursor:
#     print row[0], "\t" , row[1], "\t" , row[2], "\t", row[3]
# con.close()

