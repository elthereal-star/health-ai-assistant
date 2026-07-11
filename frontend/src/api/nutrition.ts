import request from './request'

export const createFoodRecord = (data: any) => request.post('/nutrition/food-records', data)
export const listFoodRecords = () => request.get('/nutrition/food-records')
export const recognizeFood = (data: any) => request.post('/nutrition/food-records/recognize', data)
