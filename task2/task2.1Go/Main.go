package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

func fillingMatrix (n, m int) [][]int{
	r := rand.New(rand.NewSource(time.Now().UnixNano()))
	var (
		first = r.Intn(n)
		second = r.Intn(m)
	)
	matrix := make([][]int, n, m)
	for i := range matrix {
		matrix[i] = make([]int, m)
		for j := range matrix[i] {
			if i == first && j == second {
				matrix[i][j] = 1
			} else {
				matrix[i][j] = 0
			}
		}
	}
	fmt.Println(matrix)

	return matrix
}
var wg sync.WaitGroup
func findBear(id int, sectors <-chan []int, isFindSignal chan bool){
	for sector := range sectors{
		select {
			case <-isFindSignal:
				isFindSignal <- true
				fmt.Println(id, " exit")
				defer wg.Done()
				return
			default:
				fmt.Println(id, " start searching ", sector)
				for index, i := range sector {
					time.Sleep(time.Millisecond * 100)
					if i == 1 {
						fmt.Println(id, " found bear ", index + 1, " position")
						isFindSignal <- true
						defer wg.Done()
						return
					}
				}
		}
	}
	fmt.Println(id,"End")
	defer wg.Done()
}
func main(){
	var ( //dimentions
		n = 10
		m = 10
	)
	var forestMatrix = fillingMatrix(n, m)
	sectors := make(chan []int, 10)
	isFindSignal := make(chan bool, 10)


	for w := 0; w < 3; w++ {
		wg.Add(1)
		go findBear(w, sectors, isFindSignal)
	}
	for i:=0; i < m; i++{
		sectors <- forestMatrix[i]
	}
	close(sectors)
	wg.Wait()
}