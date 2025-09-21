-- Create or update system data
DO
$$
DECLARE
    system_user varchar := 'demo-system';
    p  text[];
    -- 系統參數類別資料
    parameter_category text[] :=  ARRAY [
            ARRAY ['system-auth', '系統核身設定', '系統核身相關參數', '0', 'Y']
        ];
BEGIN
    -- 新增/更新系統參數類別
    FOREACH p SLICE 1 IN ARRAY parameter_category
    LOOP
        INSERT INTO public.sys_parameter_category(
            parameter_category_key, parameter_category_value, description, sort, is_enabled, create_user, create_date, modify_user, modify_date)
            VALUES (p[1], p[2], p[3], p[4]::int, p[5], system_user, NOW(), system_user, NOW())
            ON CONFLICT ON CONSTRAINT sys_parameter_category_pk
            DO UPDATE SET parameter_category_value=p[2], description=p[3], sort=p[4]::int, is_enabled=p[5], modify_user=system_user, modify_date=NOW();
    END LOOP;
END;
$$