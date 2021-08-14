
# InsertionSort algorithm


## @brief InsertionSort algorithm
## @param array The array to sort
def insertionSort(array):
    # Iterate through 1 to len(array)
    for i in range(1, len(array)):
        value = array[i]
        # Move elements of array, that are
        # greater than value, to one position ahead
        j = i-1
        while j >=0 and value < array[j] :
                array[j+1] = array[j]
                j -= 1
        array[j+1] = value


# Code for testing
strArray = raw_input("Enter a number array separated by commas: ")
# Turns the string into an array
strArray = strArray.strip()
array = strArray.split(",")
# Cast the string array to a int array
array = [int(i) for i in array]

insertionSort(array)
print ("Sorted array is: ")
for i in range(len(array)):
    print ("%d" %array[i])
