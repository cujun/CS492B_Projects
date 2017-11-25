#!/usr/bin/env python3

import sys
import os
import glob

import trueskill


DATA_FOLDER_PATH = './pubg_2017_gstar/'
DATA_FOLDER_SOLO = 'solo/'
DATA_FOLDER_DUO = 'duo/'
DATA_FOLDER_SQUAD = 'squad/'
DATA_FILE_PLAYER = 'players.csv'

PLAYER_NUM = 76


def main():
    # Initialize
    my_env = trueskill.TrueSkill()
    players = dict()
    with open(DATA_FOLDER_PATH + DATA_FILE_PLAYER, 'r') as f:
        for line in f:
            p = [None] * 4
            team_name, p[0], p[1], p[2], p[3] = line.strip().split(',')
            for i in range(4):
                players[p[i]] = [team_name, my_env.create_rating()]

    # Analyze solo mode game dataset
    for filename in glob.glob(DATA_FOLDER_PATH + DATA_FOLDER_SOLO + '*'):
        rating_groups = list()
        ranks = list(range(PLAYER_NUM))
        with open(filename, 'r') as f:
            for line in f:
                player_name = line.strip()
                player_trueskill = players[player_name][1]
                rating_groups.append({player_name: player_trueskill})
            rated_rating_groups = my_env.rate(rating_groups, ranks)
            for group in rated_rating_groups:
                player_name = list(group.keys())[0]
                players[player_name][1] = group[player_name]

    # Print results!
    print("==========RESULT==========\n")
    print("[PLAYER_NAME]\t\t[TEAM_NAME]\t\t[mu, sd]")
    for name, val in players.items():
        print("{:24}{:24}({:<17.15f}, {:>17.15f})".format(name, val[0], val[1].mu, val[1].sigma))
    print("\n==========================")

if __name__ == '__main__':
    main()
