#!/usr/bin/env python3

with open('a.csv') as fr, open('b.csv', 'w') as fw:
    while True:
        ok = fr.readline()
        if not ok:
            break
        res = ok.split()[2]
        fw.write(res + '\n')
        fr.readline()
        fr.readline()
        fr.readline()
