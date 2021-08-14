
# Caesar Cipher

# Store the aphabet
alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

# String to encrypt
string = str(raw_input("String to encript: ")).upper() 
# Numerical Key
key = int(raw_input("Numerical key: ")) 
# String for the result
encriptedStr = ""
  
# Iterate through every char in the string to encrypt her
for char in string:
    if char == " ":
        encriptedStr += " "
    else: 
    	# Search the position in alphabet and add the key
        op=alphabet.find(char)+key
        # Apply mod 26
        mod=int(op)%26
 		# Search in the alphabet with the new position
        encriptedStr= encriptedStr+str(alphabet[mod]) 
  
# Print the final result
print encriptedStr