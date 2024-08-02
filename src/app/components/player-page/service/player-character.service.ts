import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Character } from '../../shared/models/character/character.model';
import { CharacterUpdate } from '../../shared/models/character/characterUpdate.model';
import { CharacterCreateForm } from '../../shared/models/character/Form/characterCreateForm.model';
import { CharacterInventory } from '../../shared/models/character/characterInventory.model';
import { CharacterSkills } from '../../shared/models/character/characterSkills.model';

@Injectable({
  providedIn: 'root'
})
export class PlayerCharacterService {

  apiUrl: string = "http://localhost:8080/characters"

  constructor(private httpClient: HttpClient) { }

  userCharacters(idPlayer: number){
    return this.httpClient.get<Character[]>(this.apiUrl + `/${idPlayer}`)
  }

  editCharacter(character: CharacterUpdate, characterId?: number){
    return this.httpClient.put<CharacterUpdate>(this.apiUrl + `/update/${characterId}`, character)
  }

  createCharacter(character: CharacterCreateForm){
    return this.httpClient.post<CharacterCreateForm>(this.apiUrl + '/create', character)
  }

  getCharacterSkillsByUserId(id: number){
    return this.httpClient.get<CharacterSkills[]>(this.apiUrl + `/skills/${id}`)
  }

  getCharacterInventoryByUserId(id: number){
    return this.httpClient.get<CharacterInventory[]>(this.apiUrl + `/inventory/${id}`)
  }
}
