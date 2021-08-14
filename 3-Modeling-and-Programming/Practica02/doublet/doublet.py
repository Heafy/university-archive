
# Code to check if a chain of string is a doublet
# 
print("Introduce a set of string to check if it a doublet:")
print("Example: ROSA - ROMA - COMA - COME - CSME - BSME - CSME") 
s = input("")
# Eliminate spaces and store the words in an array
array = s.upper().split(" - ")

## @brief Compare by length all the elements in the array
## @param array The array to compare
## @return True if all the elements have the same length, 
## False in other case
def compareByLen(array):
	for i in range(0, len(array)):
		if(len(array[i]) != len(array[i+1])):
			return False
		# Special case for the last element in array
		if(len(array[len(array)-1]) != len(array[len(array)-2])):
			return False
		else:
			return True

## @brief Compare char by char the elements in the array, checking if only 
## change one character at a time
## @param array The array to compare his elements
## @return True if is a doublet, False in other case
def compareByChar(array):
	differ = 0
	for i in range(0, (len(array)-1)):
		str1 = array[i]
		str2 = array[i+1]
		for i in range(0, len(str1)):
			if(str1[i] != str2[i]):
				differ += 1
	if(differ == (len(array)-1)):
		return True
	else: 
		return False
		
print (array)

if(compareByLen(array) and compareByChar(array)):
	print("It's a doublet!")
else:
	print("It's not a doublet")
