package pac;

import java.util.Arrays;
import java.util.Scanner;

public class PriorityQueue {
	static Scanner scannerObj = new Scanner(System.in);
	static int queueSize;
	static int front = -1;
	static int rear = -1;
	static int queueArray[];

	public static void main(String[] args) {

		try {
			queueSize = getSizeOfQueue();
			queueArray = new int[queueSize];
			Arrays.fill(queueArray, Integer.MIN_VALUE);

		} catch (Exception e) {
			System.out.println(e);
		}

		boolean isValid = true;
		while (isValid) {
			try {
				System.out.println("Choose which operation you want to perform\n"
						+ "1.Add an element to queue\n" +
						"2.Remove an elemt from queue\n"
						+ "3.Check whether queue is empty or not\n"
						+ "4.Check wheher queue is full or  not\n"
						+ "5.Get peek value\n"
						+ "5.Exit");
				int input = scannerObj.nextInt();
				switch (input) {
					case 1:
						boolean isAdded = enque();
						if (isAdded)
							System.out.println("Element added successfullly");
						else
							System.out.println("Queue is full\n" +
									"Item cannot be added");
									// for (int i = 0; i < queueArray.length; i++) {
									// 	System.out.println(queueArray[i]+" "+i);
										
									// }
						break;
					case 2:
						boolean isRemoved = deque();
						if (isRemoved)
							System.out.println("Element deleted successfullly");
						else
							System.out.println("Queue is empty\n" +
									"Item cannot be deleted");

						break;
					case 3:
						boolean isQueueEmpty = isEmpty();
						if (isQueueEmpty)
							System.out.println("Queue is empty");
						else
							System.out.println("Queue is not empty");

						break;
					case 4:
						boolean isQueueFull = isEmpty();
						if (isQueueFull)
							System.out.println("Queue is full");
						else
							System.out.println("Queue is not full");
						break;
					case 5:
						int peekVal = getPeekVal();
						if (peekVal == -1)
							System.out.println("Peek Value is " + null);
						else
							System.out.println("Peek Value is " + peekVal);

						break;

					case 6:
						System.out.println("Program terminated successfully");
						isValid = false;

						break;

					default:
						System.out.println("Please select valid option");
				}

			} catch (Exception e) {
				if (e.getMessage() == null)
					System.out.println(e);
				else
					System.out.println(e.getMessage());
				scannerObj.nextLine();
			}
		}

	}

	static public int getSizeOfQueue() throws Exception {
		System.out.println("Enter size of queue");
		if(scannerObj.hasNextInt()){
			int sizeQueue = scannerObj.nextInt();
		if (sizeQueue > 0) {
			return sizeQueue;
		} else
			{
				System.out.println("Enter valid size");
			scannerObj.next();
			return getSizeOfQueue();
			}

		}
		else{
			System.out.println("Enter valid input");
			scannerObj.next();
			return getSizeOfQueue();
		}
		

	}

	static int getPeekVal() {
		int peekVal = queueArray[0];
		if (peekVal == Integer.MIN_VALUE)
			return -1;
		else
			return peekVal;

	}

	static boolean enque() {
		if (isFull()) {
			// System.out.println("Queue is full");
			return false;

		} else {

			rear++;

			System.out.println("Enter value in queue");
			queueArray[rear] = scannerObj.nextInt();
			if(queueArray[0]<queueArray[rear]){
				int temp=queueArray[0];
				queueArray[0]=queueArray[rear];
				queueArray[rear]=temp;
			}
			
			return true;
		}

	}

	static boolean isFull() {
		if (rear == queueArray.length - 1 && front < 0)
			return true;
		else
			return false;

		// if(front<0 && rear==queueArray.length-1)return true;
		// else if((rear+1)%queueArray.length-1==front)return true;
		// else return false;

	}

	static boolean deque() {
		if (isEmpty()) {
			// System.out.println("Queue is empty");
			return false;
		} else {
			if (front == -1)
				front++;
			queueArray[0] = Integer.MIN_VALUE;
			
			for(int queueIndex=queueArray.length-1;queueIndex>0;queueIndex--){
				if(queueArray[queueIndex]>queueArray[0]){
					int temp=queueArray[queueIndex];
					queueArray[queueIndex]=queueArray[0];
					queueArray[0]=temp;
				}
			}
			// sort(queueArray, 0, queueArray.length-1);

			front++;
			return true;
		}

	}

	static public boolean isEmpty() {
		if (rear < 0)
			return true;
		else
			return rear + 1 == front;

		// if(rear<0)return true;
		// else if(rear+1==front)return true;
		// else return false;

	}

	// public static void sort(int array[], int startIndex, int endIndex) {
	// 	if (startIndex > endIndex)
	// 		return;
	// 	else {
	// 		int pivotIndex = getPivotIndex(array, startIndex, endIndex);
	// 		sort(array, startIndex, pivotIndex - 1);
	// 		sort(array, pivotIndex + 1, endIndex);
	// 	}
	// }

	// public static int getPivotIndex(int array[], int startIndex, int endIndex) {
	// 	int pivot = array[startIndex];
	// 	int up = startIndex + 1;
	// 	int down = endIndex;
	// 	while (up < down) {
	// 		while (up < array.length-1 && array[up] < pivot) {
	// 			up++;

	// 		}
	// 		while (down > 0 && array[down] > pivot) {
	// 			down--;

	// 		}
			
	// 		if (up < down) {
	// 			int temp = array[up];
	// 			array[up] = array[down];
	// 			array[down] = temp;
	// 			up++;
	// 			down--;
	// 		}
	// 		else break;

	// 	}
	// 	int temp = array[down];
	// 	array[down] = array[startIndex];
	// 	array[startIndex] = temp;
	// 	System.out.println(down+"down");
	// 	return down;

	// }

}
