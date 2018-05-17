import socket
import os
import time

HOST = 'localhost'
PORT = 9876
ADDR = (HOST,PORT)
pokePath = 'C:\\Users\\bbar\\PycharmProjects\\Pokemon_DB\\Pokemons'
filelist = [f for f in os.listdir(pokePath) if os.path.isfile(os.path.join(pokePath, f))]


serv = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

serv.bind(ADDR)
serv.listen(1)

print('listening ...')

conn, addr = serv.accept()
print('client connected ... ', addr)

for f in filelist:

    pokeFile = open(os.path.join(pokePath, f), 'rb')
    #print(pokeFile.read())

    conn.send(pokeFile.read())
    #time.sleep(0.5)
    print('sending Pokemon data files ....')

print('finished sending data!')
conn.close()
print('server disconnected')








#multiprocessing.connection attempt:
# from multiprocessing.connection import Listener
#
# address = ('localhost', 6000)     # family is deduced to be 'AF_INET'
#
# with Listener(address) as listener:
#     with listener.accept() as conn:
#         print('connection accepted from', listener.last_accepted)
#
#
#         conn.send_bytes(b'hello')
