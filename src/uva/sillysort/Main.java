package uva.sillysort;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i = 0;
		int caseNumber = 1;
		Main main = new Main();
		while ( (i = sc.nextInt()) != 0) {
			int numberOfInputs = i;
			Integer[] input = new Integer[numberOfInputs];
			for (int j = 0; j < numberOfInputs; j++, i++) {
				input[j] = sc.nextInt();
			}
			System.out.println(String.format("Case %d: %d", caseNumber++, main.sort(input)));
			System.out.println();
		}
		sc.close();
	}
	
	public Integer sort(Integer[] input) {
		ArrayList<Element> elements = new ArrayList<Element>();
		int position = 0;
		for (Integer number : input) 
			elements.add(new Element(number, position++));

		Collections.sort(elements);
		fillFinalPosition(elements);
		List<ArrayList<Element>> subSets = splitIntoRelatedSubSets(elements);
		Integer totalSwap = 0;
		for (ArrayList<Element> subSetOfPairs : subSets) {
			Element pivot = elements.get(0);
			Element newPivot = subSetOfPairs.get(0);
			if (isBetterChangePivot(pivot, newPivot, subSetOfPairs.size() - 1))
				pivot = newPivot;
			else 
				totalSwap += pivot.swap(newPivot);
			
			while (!pivot.isInFinalPosition()) {
				Element toSwap = elements.get(pivot.position);
				totalSwap += pivot.swap(toSwap);
			}
		}
		
		return totalSwap;
	}

	private List<ArrayList<Element>> splitIntoRelatedSubSets(ArrayList<Element> pairs) {
		Set<Element> alocated = new HashSet<Element>(); 
		List<ArrayList<Element>> listOfSubSets = new ArrayList<ArrayList<Element>>();
		for (Element pair : pairs) 
			if (!pair.isInFinalPosition() && !alocated.contains(pair)) {
				ArrayList<Element> subSet = new ArrayList<Element>();
				do {
					subSet.add(pair);
					alocated.add(pair);
					pair = pairs.get(pair.position);
				}while (!subSet.contains(pair));
				listOfSubSets.add(subSet);
			}
		
		return listOfSubSets;
	}

	private boolean isBetterChangePivot(Element pivot, Element pair, int amountLeft) {
		return (pivot.number + pair.number) * 2 > (pair.number - pivot.number) * amountLeft;
	}
	
	private void fillFinalPosition(ArrayList<Element> pairs) {
		int i = 0;
		for (Element pair : pairs) 
			pair.finalPosition = i++;
	}
}

class Element implements Comparable<Element> {
	int number;
	int position;
	int finalPosition;

	Element(int number, int position) {
		this.number = number;
		this.position = position;
	}

	public int swap(Element pair) {
		int temp = position;
		position = pair.position;
		pair.position = temp;
		return number + pair.number;
	}

	public boolean isInFinalPosition() {
		return finalPosition == position;
	}

	@Override
	public int compareTo(Element o) {
		return number - o.number;
	}
}