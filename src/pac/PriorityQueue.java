package pac;

import java.util.Arrays;
import java.util.Scanner;

public class PriorityQueue {
	// scanner obj. for taking user input
	static Scanner scannerObj = new Scanner(System.in);
	static int front = -1;
	static int rear = -1;
	//will keep queue elements in array
	static int queueArray[];

	public static void main(String[] args) {
		queueArray = new int[16];
		Arrays.fill(queueArray, Integer.MIN_VALUE);
		boolean isValid = true;
		while (isValid) {
			try {
				System.out.println("Choose which operation you want to perform\n"
						+ "1.Add an element to queue\n" +
						"2.Remove an elemt from queue\n"
						+ "3.Check whether queue is empty or not\n"
						+ "4.Get peek value\n"
						+ "5.Exit");
				int input = scannerObj.nextInt();
				switch (input) {

					case 1:
						boolean isAdded = enque();
						if (isAdded)
							System.out.println("Element added successfullly");
						printArray();
						break;

					case 2:
						boolean isRemoved = deque();
						if (isRemoved) {
							System.out.println("Element deleted successfullly");
							printArray();
						} else
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
						int peekVal = getPeekVal();
						if (peekVal == -1)
							System.out.println("Peek Value is m" + null);
						else
							System.out.println("Peek Value is " + peekVal);
						break;

					case 5:
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

	/**
	 * print the elements present in the queue
	 */
	public static void printArray() {
		System.out.print("Elements of queue are ");
		for (int arrayIndex = 0; arrayIndex < queueArray.length; arrayIndex++) {
			if (queueArray[arrayIndex] != Integer.MIN_VALUE)
				System.out.print(queueArray[arrayIndex] + "  ");

		}
		System.out.println();
	}

	/**
	 * will get peak value of the queue
	 * 
	 * @return peek value of the queue
	 */
	static int getPeekVal() {
		int peekVal = queueArray[0];
		if (peekVal == Integer.MIN_VALUE)
			return -1;
		else
			return peekVal;
	}

	/**
	 * will add an element to the queue
	 * 
	 * @return whether an element is added or not
	 */
	static boolean enque() {
		if (isFull()) {
			int size = queueArray.length;
			int newArray[] = new int[queueArray.length];

			for (int queueArrayIndex = 0; queueArrayIndex < queueArray.length; queueArrayIndex++) {
				newArray[queueArrayIndex] = queueArray[queueArrayIndex];
			}
			queueArray = new int[size + 1];
			queueArray[queueArray.length-1]=Integer.MIN_VALUE;
			
			for (int newArrayIndex = 0; newArrayIndex < newArray.length; newArrayIndex++) {
				queueArray[newArrayIndex] = newArray[newArrayIndex];
			}
		}

		rear++;
		System.out.println("Enter value in queue");
		queueArray[rear] = scannerObj.nextInt();
		if (queueArray[0] < queueArray[rear]) {
			int temp = queueArray[0];
			queueArray[0] = queueArray[rear];
			queueArray[rear] = temp;
		}
		return true;
	}

	/**
	 * will check whether queue is full or not
	 * 
	 * @return boolean value, whether queue is full or not
	 */
	static boolean isFull() {
		if (rear == queueArray.length - 1 && front < 0)
			return true;
		else
			return false;
	}

	/**
	 * will delete element from the queue
	 * 
	 * @return whether an element is deleted or not
	 */
	static boolean deque() {
		if (isEmpty()) {
			return false;
		} else {
			if (front == -1)
				front++;
			queueArray[0] = Integer.MIN_VALUE;

			for (int queueIndex = queueArray.length - 1; queueIndex > 0; queueIndex--) {
				if (queueArray[queueIndex] > queueArray[0]) {
					int temp = queueArray[queueIndex];
					queueArray[queueIndex] = queueArray[0];
					queueArray[0] = temp;
				}
			}
			front++;
			return true;
		}
	}

	/**
	 * will check whether queue is empty or not
	 * 
	 * @return boolean value, whether queue is empty or not
	 */
	static public boolean isEmpty() {
		if (rear < 0)
			return true;
		else
			return rear + 1 == front;
	}
}
