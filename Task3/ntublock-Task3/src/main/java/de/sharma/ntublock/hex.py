import hashlib
import sys

if len(sys.argv) > 1:
    fileName = sys.argv[1]
else:
    fileName = 'path.py'
    
    
hasher = hashlib.sha256()
with open(fileName, 'rb') as f:
    hasher.update(f.read())
    print(hasher.hexdigest())