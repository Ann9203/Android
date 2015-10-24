package com.itheima.domain;
import com.itheima.domain.Person;
import com.itheima.domain.Pet;
interface IPet{
	List<Pet> getPets(in Person owner);
}