import request from './request'

export const createExerciseRecord = (data: any) => request.post('/exercise/exercise-records', data)
export const listExerciseRecords = () => request.get('/exercise/exercise-records')
